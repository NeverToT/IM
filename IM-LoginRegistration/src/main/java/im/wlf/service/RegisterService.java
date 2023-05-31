package im.wlf.service;

import im.wlf.entity.dto.RegisterDTO;
import im.wlf.entity.param.LoginRegisterFromParam;

public interface RegisterService {

    RegisterDTO register(LoginRegisterFromParam registerFrom, String ip);
}
