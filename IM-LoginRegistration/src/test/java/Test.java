import com.alibaba.fastjson2.JSON;
import im.wlf.entity.param.LoginRegisterFromParam;
import im.wlf.utils.JwtUtils;
import impl.CaptchaTest;
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

import javax.annotation.Resource;
import javax.annotation.Resources;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Test {
    public String str;

    @org.junit.Test
    public void JwtTest() throws InterruptedException {
        Map<String, Object> map = new HashMap<>();
        map.put("house", "2");
        map.put("house2", "3");
        String token = JwtUtils.getToken(map);
        log.info("token is {}", token);
        Claims claims = JwtUtils.getClaims(token);
        System.out.println(claims);

        System.out.println(JwtUtils.verifyToken(token));
        this.str = token;
        if (JwtUtils.verifyToken(token)){
            log.info("验证通过");
        }else {
            log.info("验证不通过");
        }
    }
    @Value("${jwt.token.secret}")
    String token;
    @org.junit.Test
    public void JWT() {
        System.out.println(token);
    }

    @org.junit.Test
    public void test() {
        String phoneNum = MakePhone.generatePhoneNumber();
        LoginRegisterFromParam user = new LoginRegisterFromParam("1", "2", "3", "4");

        String userStr = JSON.toJSONString(user);

        try {
            System.out.println(CaptchaTest.captcha("http://localhost:1010/register/register", userStr, "POST"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private StringRedisTemplate redisTemplate;
    @org.junit.Test
    public void RedisTest(){
        System.out.println(redisTemplate);

    }
}
