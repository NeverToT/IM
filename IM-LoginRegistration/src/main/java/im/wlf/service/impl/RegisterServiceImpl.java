package im.wlf.service.impl;

import im.wlf.entity.dto.RegisterDTO;
import im.wlf.entity.param.LoginRegisterFromParam;
import im.wlf.mapper.RegisterMapper;
import im.wlf.mapper.impl.RegisterMapperImpl;
import im.wlf.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    RegisterMapperImpl registerService;
    @Override
    public RegisterDTO register(LoginRegisterFromParam registerFrom) {
        RegisterDTO registerDTO = registerService.register(registerFrom);
        return registerDTO;
    }
}
