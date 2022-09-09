package com.company.todolist.repository;

import com.company.todolist.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StateRepository extends JpaRepository<State, Long> {
    State findByName(String name);
    List<State> findByOrderByIdAsc();
}
