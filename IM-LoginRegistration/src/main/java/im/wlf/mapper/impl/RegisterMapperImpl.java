package im.wlf.mapper.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import im.wlf.entity.User;
import im.wlf.entity.dto.RegisterDTO;
import im.wlf.entity.param.LoginRegisterFromParam;
import im.wlf.mapper.LoginRegisterMpMapper;
import im.wlf.mapper.RegisterMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class RegisterMapperImpl implements RegisterMapper {

    @Autowired
    private LoginRegisterMpMapper mpMapper;

    /**
     * 手机号和邮箱一一对应
     * @return
     */
    @Override
    public RegisterDTO register(LoginRegisterFromParam registerFrom) {
        System.out.println(registerFrom);

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.and(wrapper ->
                wrapper.eq(User::getEmail, registerFrom.getEmail())
                        .or()
                        .eq(User::getPhone, registerFrom.getPhone()));

        Assert.isTrue(mpMapper.selectCount(queryWrapper) == 0L, "用户已存在");
        User user = new User();
        BeanUtils.copyProperties(registerFrom, user);
        mpMapper.insert(user);
        return new RegisterDTO(user.getEmail(), user.getPhone());
    }
}
