package im.wlf.entity.param;

import lombok.Data;

@Data
public class LoginRegisterFromParam {
    String email;
    String phone;
    String password;
    String code;
    String userName;

    public LoginRegisterFromParam() {
    }

    public LoginRegisterFromParam(String email, String phone, String password, String userName) {
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.userName = userName;
    }

    public LoginRegisterFromParam(String email, String phone, String password, String code, String userName) {
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.code = code;
        this.userName = userName;
    }
}
