package im.wlf.controller;

import im.common.utils.Result;
import im.common.utils.StatusCode;
import im.wlf.entity.dto.RegisterDTO;
import im.wlf.entity.param.LoginRegisterFromParam;
import im.wlf.mapper.impl.RegisterMapperImpl;
import im.wlf.service.impl.RegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterServiceImpl registerService;

    @PostMapping("/register")
    public Result<RegisterDTO> register(@Valid @RequestBody LoginRegisterFromParam registerFrom, HttpServletRequest request){
        String ipAddress = request.getRemoteAddr();
        String header = request.getHeader("Token");
        RegisterDTO registerDTO = registerService.register(registerFrom,ipAddress);
        return Result.<RegisterDTO>ok("ok").data(registerDTO).code(StatusCode.SUCCESS);
    }
}
