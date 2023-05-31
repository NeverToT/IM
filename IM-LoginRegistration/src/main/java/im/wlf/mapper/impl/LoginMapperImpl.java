package im.wlf.mapper.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import im.common.constants.RedisKey;
import im.wlf.entity.User;
import im.wlf.entity.param.LoginRegisterFromParam;
import im.wlf.exception.LoginException;
import im.wlf.interceptor.UserHolder;
import im.wlf.mapper.LoginRegisterMpMapper;
import im.wlf.mapper.LoginMapper;
import im.common.utils.ValidCheckUtil;
//import im.wlf.utils.IPUtils;
import im.wlf.utils.JwtUtils;
import im.wlf.utils.VerificationCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Repository
@Slf4j
public class LoginMapperImpl implements LoginMapper {

    @Autowired
    LoginRegisterMpMapper loginRegisterMpMapper;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public String loginByEmail(LoginRegisterFromParam loginFrom, String ipAddress) {
        String email = loginFrom.getEmail();
        String password = loginFrom.getPassword();
        Assert.isTrue(ValidCheckUtil.isValidEmail(email), "邮箱不合法");

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, email);
        User user = loginRegisterMpMapper.selectOne(queryWrapper);

        Assert.isTrue(user != null, "用户不存在，请注册");
        Assert.isTrue(user.getPassword().equals(password), "密码错误");
        Map<String,Object> userInfo = setUserInfo(user,loginFrom);
        String token = JwtUtils.getToken(userInfo);
        return token;
    }

    @Override
    public String captcha(String phone) {
        Assert.isTrue(ValidCheckUtil.isValidPhoneNumber(phone), "手机号不合法");
        if (redisTemplate.opsForValue().get(RedisKey.CAPTCHA_PHONE_KEY + phone) != null) {
            throw new LoginException.CaptchaLimitException();
        }
        String code = VerificationCode.generateVerificationCode(4);
        redisTemplate.opsForValue().set(RedisKey.CAPTCHA_PHONE_KEY + phone, code, 1, TimeUnit.MINUTES);
        return code;
    }

    @Override
    public String loginByPhone(LoginRegisterFromParam loginFrom, String ipAddress) {
        String phone = loginFrom.getPhone();
        String code = loginFrom.getCode();
        if (phone==null||code==null){
            throw new LoginException.ParamNullException("请完整填写手机号验证码");
        }
        Assert.isTrue(ValidCheckUtil.isValidPhoneNumber(phone), "手机号不合法");
        String catchCaptcha = redisTemplate.opsForValue().get(RedisKey.CAPTCHA_PHONE_KEY + phone);
        if (catchCaptcha==null){
            throw new LoginException.ParamNullException("验证码过期，请重新发送");
        }
        if (!catchCaptcha.equals(code)){
            throw new LoginException.ParamNullException("验证码错误");
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getId).eq(User::getPhone,"17764180241");
        User user = loginRegisterMpMapper.selectOne(queryWrapper);
        Map<String,Object> userInfo = setUserInfo(user,loginFrom);
        String token = JwtUtils.getToken(userInfo);

        return token;
    }
    private Map<String,Object> setUserInfo(User user,LoginRegisterFromParam loginFrom){
        Map<String,Object> userInfo = new HashMap<>();
        userInfo.put("userId",user.getId());
        userInfo.put("email",loginFrom.getEmail());
        userInfo.put("phone",loginFrom.getPhone());
        userInfo.put("loginTime",String.valueOf(new Date().getTime()));
        return userInfo;
    }
}


