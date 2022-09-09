package com.company.todolist.dto;

import com.company.todolist.model.ToDo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ToDoResponse {
    private Long id;
    private String title;
    public ToDoResponse(ToDo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
    }
}
