package com.company.todolist.controller;

import com.company.todolist.dto.UserCreateRequest;
import com.company.todolist.dto.UserResponse;
import com.company.todolist.model.User;
import com.company.todolist.service.RoleService;
import com.company.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;


    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    List<UserResponse> getAll() {
        return userService.getAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    UserResponse getById(@PathVariable long id) {
        return new UserResponse(userService.readById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> create(@RequestBody UserCreateRequest UserCreateRequest) {

        User user = new User();
        user.setFirstName(UserCreateRequest.getFirstName());
        user.setLastName(UserCreateRequest.getLastName());
        user.setEmail(UserCreateRequest.getEmail());
        user.setPassword(UserCreateRequest.getPassword());
        user.setRole(roleService.getByName(UserCreateRequest.getRole()));
        user = userService.create(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN') or authentication.principal.user.id == @userService.readById(#id)")
    ResponseEntity<?> update(@PathVariable long id, @RequestBody UserCreateRequest userCreateRequest) {

        if(userService.readById(id) == null){
            return ResponseEntity.noContent().build();
        }

        User user = new User();
        user.setId(id);
        user.setFirstName(userCreateRequest.getFirstName());
        user.setLastName(userCreateRequest.getLastName());
        user.setEmail(userCreateRequest.getEmail());
        user.setPassword(userCreateRequest.getPassword());
        user.setRole(roleService.getByName(userCreateRequest.getRole()));
        userService.update(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAuthority('ADMIN') or authentication.principal.user.id == @userService.readById(#id)")
    void delete(@PathVariable long id) {
        userService.delete(id);
    }
}
