package im.wlf.service.impl;

//import im.wlf.mapper.ImUserMapper;
import im.wlf.entity.param.LoginRegisterFromParam;
        import im.wlf.mapper.impl.LoginMapperImpl;
import im.wlf.service.LoginService;
        import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class LoginServiceImpl  implements LoginService {
    @Autowired
    LoginMapperImpl loginMapper;
    public String loginByEmail(LoginRegisterFromParam loginFrom , String ipAddress) {
       String token = loginMapper.loginByEmail(loginFrom,ipAddress);

        return token;
    }

    @Override
    public String captcha(String phone) {
        String code = loginMapper.captcha(phone);
        return code;
    }

    @Override
    public String loginByPhone(LoginRegisterFromParam loginFrom, String ipAddress) {
        String token = loginMapper.loginByPhone(loginFrom,ipAddress);
        return token;
    }

}
