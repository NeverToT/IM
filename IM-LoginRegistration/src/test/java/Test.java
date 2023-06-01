import com.alibaba.fastjson2.JSON;
import im.wlf.entity.param.LoginRegisterFromParam;
import im.wlf.utils.JwtUtils;
import impl.HttpUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import util.MakePhone;
import util.RandomEmailGenerator;

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Test {

    @org.junit.Test
    public void register() {
        String phoneNum = MakePhone.generatePhoneNumber();
        String email = RandomEmailGenerator.generateRandomEmail();
        System.out.println("随机生成的手机号:"+phoneNum);
        System.out.println("随机生成的邮箱:"+email);
        LoginRegisterFromParam user = new LoginRegisterFromParam(email, phoneNum, "123456qq", "zglbcvip");
        String userStr = JSON.toJSONString(user);
        try {
            System.out.println(HttpUtils.sendHttpRequest("http://localhost:1010/register/register", userStr, "POST",null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 手机号:15999666858
     * 验证码：9225
     * 邮箱：test9221@aol.com
     * 密码："123456qq"
     * 测试步骤：通过第一个方法生成随机的手机号，密码注册，把信息记录到这里
     * 然后用生成的手机号发送验证码，测试通过手机登陆
     * 通过生成的邮箱和密码，测试通过密码登陆
     */
    @org.junit.Test
    public void captcha() throws IOException {
        String captcha = HttpUtils.sendHttpRequest("http://localhost:1010/login/captcha/15999666858", null, "POST",null);
        System.out.println(captcha);

    }

    @org.junit.Test
    public void loginByPhone() {

        LoginRegisterFromParam user = new LoginRegisterFromParam(null, "15999666858",null,"9225", null);
        String userStr = JSON.toJSONString(user);
        String token = null;
        try {
          token =  HttpUtils.sendHttpRequest("http://localhost:1010/login/loginByPhone", userStr, "POST",null);
          log.info("生成的token{}",token);
          //测试被拦截器拦截需要验证token的接口
          String tokenTure = HttpUtils.sendHttpRequest("http://localhost:1010/test/tokenTest", userStr, "POST",token);
          log.info("token是否正确：{}",tokenTure);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void loginByEmail() {

        LoginRegisterFromParam user = new LoginRegisterFromParam("test9221@aol.com", null,"123456qq",null, null);
        String userStr = JSON.toJSONString(user);
        String token = null;
        try {
            token =  HttpUtils.sendHttpRequest("http://localhost:1010/login/loginByEmail", userStr, "POST",null);
            log.info("生成的token{}",token);

            String tokenTure = HttpUtils.sendHttpRequest("http://localhost:1010/test/tokenTest", userStr, "POST",token);
            log.info("token是否正确：{}",tokenTure);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
