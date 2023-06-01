package im.wlf.mapper;

import im.wlf.entity.dto.RegisterDTO;
import im.wlf.entity.param.LoginRegisterFromParam;

public interface RegisterMapper {
     RegisterDTO register(LoginRegisterFromParam registerFrom);
}
