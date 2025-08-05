package com.learning.learning.controller;

import com.learning.learning.entity.TodoItem;
import com.learning.learning.dto.TodoItemCreateRequest;
import com.learning.learning.dto.TodoItemUpdateRequest;
import com.learning.learning.service.TodoItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*")
public class TodoItemController {
    
    @Autowired
    private TodoItemService todoItemService;
    
    // GET /api/todos - 全てのTodoアイテムを取得
    @GetMapping
    public ResponseEntity<List<TodoItem>> getAllTodos(
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false) String search) {
        
        List<TodoItem> todos;
        
        if (search != null && !search.trim().isEmpty()) {
            todos = todoItemService.searchTodoItemsByTitle(search);
        } else if (completed != null) {
            todos = todoItemService.getTodoItemsByStatus(completed);
        } else {
            todos = todoItemService.getAllTodoItems();
        }
        
        return ResponseEntity.ok(todos);
    }
    
    // GET /api/todos/{id} - 特定のTodoアイテムを取得
    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getTodoById(@PathVariable Long id) {
        return todoItemService.getTodoItemById(id)
            .map(todo -> ResponseEntity.ok(todo))
            .orElse(ResponseEntity.notFound().build());
    }
    
    // POST /api/todos - 新しいTodoアイテムを作成
    @PostMapping
    public ResponseEntity<TodoItem> createTodo(@Valid @RequestBody TodoItemCreateRequest request) {
        TodoItem createdTodo = todoItemService.createTodoItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }
    
    // PUT /api/todos/{id} - Todoアイテムを更新
    @PutMapping("/{id}")
    public ResponseEntity<TodoItem> updateTodo(
            @PathVariable Long id, 
            @Valid @RequestBody TodoItemUpdateRequest request) {
        
        return todoItemService.updateTodoItem(id, request)
            .map(todo -> ResponseEntity.ok(todo))
            .orElse(ResponseEntity.notFound().build());
    }
    
    // DELETE /api/todos/{id} - Todoアイテムを削除
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        if (todoItemService.deleteTodoItem(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
