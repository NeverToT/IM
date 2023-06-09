package im.wlf.controller;


//import im.wlf.service.ImUserService;
//import im.wlf.service.impl.ImUserServiceImp;

import im.common.utils.Result;
import im.common.utils.StatusCode;
import im.wlf.entity.param.LoginRegisterFromParam;
//import im.wlf.service.impl.ImUserServiceImp;
//import im.wlf.interceptor1 .TokenInterceptor;
import im.wlf.interceptor.UserHolder;
import im.wlf.service.impl.LoginServiceImpl;
import im.wlf.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *通过手机+验证码｜｜邮箱+密码登陆
 * @author wlf
 * @since 2023-05-21
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginServiceImpl loginService;

    /**
     * 志哥讲一下怎么在加一层，让controller返回不用都是result，再看看我用接口隔离MP对不对，还有yml怎么读取properties
     */
    @PostMapping("/loginByEmail")
    public String loginByEmail(@RequestBody LoginRegisterFromParam loginFrom) {

        String token = loginService.loginByEmail(loginFrom);
        return token;
    }

    @PostMapping("/loginByPhone")
    public String loginByPhone(@RequestBody LoginRegisterFromParam loginFrom) {

        String token = loginService.loginByPhone(loginFrom);
        return token;
    }

    @PostMapping("/captcha/{phone}")
    public String captcha(@PathVariable String phone) {
        return loginService.captcha(phone);
    }

}

