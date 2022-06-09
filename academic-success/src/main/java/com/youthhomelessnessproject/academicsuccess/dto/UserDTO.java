package com.youthhomelessnessproject.academicsuccess.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// UserDTO only used for login flows

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String role;

}
