package im.wlf.entity.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    String email;
    String phone;

    public RegisterDTO() {

    }

    public RegisterDTO(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }
}
