package com.company.todolist.service;


import com.company.todolist.model.Role;

import java.util.List;

public interface RoleService {
    Role create(Role role);
    Role readById(long id);
    Role update(Role role);
    void delete(long id);
    List<Role> getAll();
    Role getByName(String name);

}
