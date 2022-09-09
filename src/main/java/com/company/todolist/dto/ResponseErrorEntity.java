package com.company.todolist.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseErrorEntity {
    private String code;
    private String message;

    public ResponseErrorEntity(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
