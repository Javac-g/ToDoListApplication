package com.company.todolist.repository;

import com.company.todolist.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
//    @Query(value = "select * " +
//                   "from todos\n" +
//                   "    where owner_id = :userId\n" +
//                   "union\n" +
//                   "select * " +
//                   "from todos t inner join todo_collaborator tc\n" +
//                   "    on t.id = tc.todo_id and tc." +
//                   "collaborator_id = :userId;", nativeQuery = true)
    @Query(value = "select * " +
                   "from todos\n" +
                   "    where owner_id = :userId\n", nativeQuery = true)
    List<ToDo> getByUserId(long userId);
}
