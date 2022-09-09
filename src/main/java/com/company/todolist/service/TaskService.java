package com.company.todolist.service;

import com.company.todolist.model.Task;

import java.util.List;

public interface TaskService {
    Task create(Task task);
    Task readById(long id);
    Task update(Task task);
    void delete(long id);
    List<Task> getAll();

    List<Task> getByTodoId(long todoId);
}
