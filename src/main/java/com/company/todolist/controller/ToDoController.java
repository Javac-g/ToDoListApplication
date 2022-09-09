package com.company.todolist.controller;

import com.company.todolist.dto.ToDoCreateRequest;
import com.company.todolist.dto.ToDoResponse;
import com.company.todolist.dto.UserResponse;
import com.company.todolist.model.ToDo;
import com.company.todolist.model.User;
import com.company.todolist.service.ToDoService;
import com.company.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users/{user_id}/todos")
public class ToDoController {

    @Autowired
    ToDoService toDoService;
    @Autowired
    UserService userService;

    @GetMapping
    ResponseEntity<?> getAll(@PathVariable("user_id") long userId) {

        User user = userService.readById(userId);
        if (user == null) {
            ResponseEntity.noContent().build();
        }

        List<ToDoResponse> toDoResponse = toDoService.getByUserId(userId).stream()
                .map(ToDoResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(toDoResponse);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> getById(@PathVariable("id") long id) {
        ToDo toDo = toDoService.readById(id);
        if (toDo == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(new ToDoResponse(toDo));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> create(@PathVariable("user_id") long userId, @RequestBody ToDoCreateRequest toDoCreateRequest) {

        User user = userService.readById(userId);
        if (user == null) {
            return ResponseEntity.noContent().build();
        }

        ToDo toDo = new ToDo();
        toDo.setTitle(toDoCreateRequest.getTitle());
        toDo.setOwner(userService.readById(userId));

        toDo = toDoService.create(toDo);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(toDo.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> update(@PathVariable("user_id") long userId,
                             @PathVariable("id") long id,
                             @RequestBody ToDoCreateRequest toDoCreateRequest) {

        User user = userService.readById(userId);
        if (user == null) {
            return ResponseEntity.noContent().build();
        }
        ToDo oldToDo = toDoService.getByUserId(userId).stream()
                .filter(t -> t.getId() == id)
                .findFirst().orElse(null);

        if (oldToDo == null) {
            return ResponseEntity.noContent().build();
        }

        ToDo toDo = new ToDo();
        toDo.setId(id);
        toDo.setCreatedAt(oldToDo.getCreatedAt());
        toDo.setTitle(toDoCreateRequest.getTitle());
        toDo.setOwner(oldToDo.getOwner());
        toDo.setCollaborators(oldToDo.getCollaborators());

        toDo = toDoService.update(toDo);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(toDo.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void delete(@PathVariable long id) {
        toDoService.delete(id);
    }

    @GetMapping("/{id}/collaborators")
    ResponseEntity<?> getAllCollabs(@PathVariable("id") long todoId) {
        List<UserResponse> collabResponse = toDoService.readById(todoId).getCollaborators().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(collabResponse);
    }

    @PutMapping("/{id}/collaborators")
    ResponseEntity<?> updateCollabs(@PathVariable("user_id") long userId,
                                    @PathVariable("id") long id,
                                    @RequestBody ToDoCreateRequest toDoCreateRequest) {
        User user = userService.readById(userId);
        if (user == null) {
            return ResponseEntity.noContent().build();
        }
        ToDo oldToDo = toDoService.getByUserId(userId).stream()
                .filter(t -> t.getId() == id)
                .findFirst().orElse(null);

        if (oldToDo == null) {
            return ResponseEntity.noContent().build();
        }

        ToDo toDo = new ToDo();
        toDo.setId(id);
        toDo.setCreatedAt(oldToDo.getCreatedAt());
        toDo.setTitle(toDoCreateRequest.getTitle());
        toDo.setOwner(oldToDo.getOwner());
        toDo.setCollaborators(oldToDo.getCollaborators());

        toDo = toDoService.update(toDo);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(toDo.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
