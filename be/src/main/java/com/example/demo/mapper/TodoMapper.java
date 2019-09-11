package com.example.demo.mapper;

import com.example.demo.model.Todo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface TodoMapper {
    List<Todo> findAll();
    void insertTodo(Todo todo);
    void deleteTodo(int id);
    void updateTodo(Todo todo);
    void updatePriority(Todo todo);
    void updateCompleted(Todo todo);
    void updateDate(Todo todo);

}
