package com.Thienbao.booking.payload.request;

import com.Thienbao.booking.model.USER_SEX;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateUserRequest {

    @NotBlank(message = "Name not null or empty or blank")
    private String fullName;

    private MultipartFile fileAvatar;

    private  String address;

    private String phone;

    private USER_SEX sex;
}
