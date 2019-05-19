package com.example.demo.controller;

import com.example.demo.model.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://hyeon-todoweb.s3-website.ap-northeast-2.amazonaws.com")
@RestController
public class TodoController {
    private static Map<Integer, Todo> todoRepo = new HashMap<>();
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
        todoRepo.get(id).setCompleted(true);

        return new ResponseEntity<>("Complete is updated successsfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/todos/{id}/date", method = RequestMethod.PUT)
    public ResponseEntity<Object> setTodoDate(@PathVariable("id") int id, @RequestBody Todo todo) {
        todoRepo.get(id).setDate(todo.getDate());

        return new ResponseEntity<>("Date is updated successsfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/todos/{id}/priority", method = RequestMethod.PUT)
    public ResponseEntity<Object> setTodoPritory(@PathVariable("id") int id, @RequestBody Todo todo) {
        todoRepo.get(id).setPriority(todo.getPriority());

        return new ResponseEntity<>("Priority is updated successsfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/todos/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> deleteTodo(@PathVariable("id") int id) {
        todoRepo.remove(id);

        return new ResponseEntity<>("Todo is deleted successsfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/todos/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateTodo(@PathVariable("id") int id, @RequestBody Todo todo) {
        todoRepo.get(id).setTitle(todo.getTitle());
        todoRepo.get(id).setContent(todo.getContent());

        return new ResponseEntity<>("Todo is updated successsfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/todos", method = RequestMethod.POST)
    public ResponseEntity<Object> createTodo(@RequestBody Todo todo) {
        todoRepo.put(todo.getId(), todo);

        return new ResponseEntity<>("Todo is created successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<Object> getList() {
        return new ResponseEntity<>(todoRepo.values(), HttpStatus.OK);
    }
}
