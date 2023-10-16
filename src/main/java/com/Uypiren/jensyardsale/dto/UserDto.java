package com.Uypiren.jensyardsale.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class UserDto {
    private String userName;

    private String password;

    private String role;

    private String fullName;
    private String token;


    public UserDto(String userName, String password, String role, String fullName) {
        this.userName = userName;
        this.password =password;
        this.role =  role;
        this.fullName =  fullName;
    }
}
