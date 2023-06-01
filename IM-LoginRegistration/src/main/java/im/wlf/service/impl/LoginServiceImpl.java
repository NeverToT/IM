package im.wlf.service.impl;

//import im.wlf.mapper.ImUserMapper;
import im.wlf.entity.param.LoginRegisterFromParam;
        import im.wlf.mapper.impl.LoginMapperImpl;
import im.wlf.service.LoginService;
        import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 这个理论也不是很清楚，因为我一直认为是controller调用service,service再去调用mapper，然后mapper就直接能够调用xml文件去crud
 * 以前mp的crud我是直接写在这里的，但是现在既然要隔离mp，那我这个service就什么都不写，还有对应的mapper里也什么都不写，
 * 在对应的mapper的实现类里去调用mp，我也不知道这么做对不对，感觉有点蠢
 */
@Service
public class LoginServiceImpl  implements LoginService {
    @Autowired
    LoginMapperImpl loginMapper;
    public String loginByEmail(LoginRegisterFromParam loginFrom) {
       String token = loginMapper.loginByEmail(loginFrom);

        return token;
    }

    @Override
    public String captcha(String phone) {
        String code = loginMapper.captcha(phone);
        return code;
    }

    @Override
    public String loginByPhone(LoginRegisterFromParam loginFrom) {
        String token = loginMapper.loginByPhone(loginFrom);
        return token;
    }

}
