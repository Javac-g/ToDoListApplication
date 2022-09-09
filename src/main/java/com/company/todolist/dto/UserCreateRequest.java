package com.company.todolist.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCreateRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

}
