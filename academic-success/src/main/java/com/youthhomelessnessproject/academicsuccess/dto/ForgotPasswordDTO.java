package com.youthhomelessnessproject.academicsuccess.dto;

import lombok.Data;

@Data
public class ForgotPasswordDTO {

    private String username;
    private String newPassword;
    private String newConfirmPassword;
}
