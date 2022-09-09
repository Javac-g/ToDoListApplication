package com.company.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Data
public class TokenResponse {
    private String token;
}
