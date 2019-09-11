package com.example.demo.service;

import com.example.demo.mapper.TodoMapper;
import com.example.demo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class TodoService {
    @Autowired
    private TodoMapper todoMapper;

    public List<Todo> findAll() {
        return todoMapper.findAll();
    }

    public void insertTodo(Todo todo) {
        todoMapper.insertTodo(todo);
    }

    public void deleteTodo(int id) {
        todoMapper.deleteTodo(id);
    }

    public void updateTodo(Todo todo) {
        todoMapper.updateTodo(todo);
    }

    public void updatePriority(Todo todo) {
        todoMapper.updatePriority(todo);
    }

    public void updateCompleted(Todo todo) {
        todoMapper.updateCompleted(todo);
    }

    public void updateDate(Todo todo) {
        todoMapper.updateDate(todo);
    }

}
