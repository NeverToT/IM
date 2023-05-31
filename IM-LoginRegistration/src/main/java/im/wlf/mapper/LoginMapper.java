package im.wlf.mapper;

import im.wlf.entity.param.LoginRegisterFromParam;


public interface LoginMapper {
//    @Select("SELECT * FROM im_user WHERE email = #{email}")
    String loginByEmail(LoginRegisterFromParam loginFrom, String ipAddress);

    String captcha(String phone);

    String loginByPhone(LoginRegisterFromParam loginFrom, String ipAddress);
}
