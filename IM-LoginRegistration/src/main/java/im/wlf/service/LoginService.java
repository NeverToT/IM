package im.wlf.service;

import im.wlf.entity.param.LoginRegisterFromParam;


public interface LoginService {
   String loginByEmail(LoginRegisterFromParam loginFrom, String ipAddress);
   String captcha(String phone);
   String loginByPhone(LoginRegisterFromParam loginFrom, String ipAddress);

}
