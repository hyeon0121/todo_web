package com.example.demo.controller;

import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class TodoController {
    private static Map<Integer, Todo> todoRepo = new HashMap<>();

    @Autowired
    private TodoService todoService;
//
//    static {
//        Todo todo = new Todo();
//        todo.setId(1);
//        todo.setTitle("청소");
//        todo.setContent("청소하기");
//        todo.setPriority(1);
//        todoRepo.put(todo.getId(), todo);
//
//    }

    @RequestMapping(value = "/todos/{id}/completed", method = RequestMethod.PUT)
    public ResponseEntity<Object> setTodoComplete(@PathVariable("id") int id, @RequestBody Todo todo) {
        //todoRepo.get(id).setCompleted(true);

        // completed update
        todoService.updateCompleted(todo);

        return new ResponseEntity<>("Complete is updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/todos/{id}/date", method = RequestMethod.PUT)
    public ResponseEntity<Object> setTodoDate(@PathVariable("id") int id, @RequestBody Todo todo) {
        //todoRepo.get(id).setDate(todo.getDate());

        // date update
        todoService.updateDate(todo);

        return new ResponseEntity<>("Date is updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/todos/{id}/priority", method = RequestMethod.PUT)
    public ResponseEntity<Object> setTodoPritory(@PathVariable("id") int id, @RequestBody Todo todo) {
        //todoRepo.get(id).setPriority(todo.getPriority());

        // priority update
        todoService.updatePriority(todo);

        return new ResponseEntity<>("Priority is updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/todos/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> deleteTodo(@PathVariable("id") int id) {
//        todoRepo.remove(id);

        // delete
        todoService.deleteTodo(id);
        return new ResponseEntity<>("Todo is deleted successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/todos/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateTodo(@PathVariable("id") int id, @RequestBody Todo todo) {
//        todoRepo.get(id).setTitle(todo.getTitle());
//        todoRepo.get(id).setContent(todo.getContent());

        // update
        todoService.updateTodo(todo);

        return new ResponseEntity<>("Todo is updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/todos", method = RequestMethod.POST)
    public ResponseEntity<Object> createTodo(@RequestBody Todo todo) {
        //todoRepo.put(todo.getId(), todo);

        // insert
        todoService.insertTodo(todo);
        return new ResponseEntity<>("Todo is created successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Todo>> getTodoList() {
        ResponseEntity<List<Todo>> entity = null;
        try {
            entity = new ResponseEntity<>(todoService.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;

    }
}