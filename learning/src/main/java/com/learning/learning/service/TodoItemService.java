package com.learning.learning.service;

import com.learning.learning.entity.TodoItem;
import com.learning.learning.dto.TodoItemCreateRequest;
import com.learning.learning.dto.TodoItemUpdateRequest;
import com.learning.learning.repository.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService {
    
    @Autowired
    private TodoItemRepository todoItemRepository;
    
    public List<TodoItem> getAllTodoItems() {
        return todoItemRepository.findByOrderByCreatedAtDesc();
    }
    
    public Optional<TodoItem> getTodoItemById(Long id) {
        return todoItemRepository.findById(id);
    }
    
    public List<TodoItem> getTodoItemsByStatus(Boolean completed) {
        return todoItemRepository.findByCompleted(completed);
    }
    
    public List<TodoItem> searchTodoItemsByTitle(String title) {
        return todoItemRepository.findByTitleContaining(title);
    }
    
    public TodoItem createTodoItem(TodoItemCreateRequest request) {
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle(request.getTitle());
        todoItem.setDescription(request.getDescription());
        return todoItemRepository.save  (todoItem);
    }
    
    public Optional<TodoItem> updateTodoItem(Long id, TodoItemUpdateRequest request) {
        return todoItemRepository.findById(id)
            .map(todoItem -> {
                if (request.getTitle() != null) {
                    todoItem.setTitle(request.getTitle());
                }
                if (request.getDescription() != null) {
                    todoItem.setDescription(request.getDescription());
                }
                if (request.getCompleted() != null) {
                    todoItem.setCompleted(request.getCompleted());
                }
                return todoItemRepository.save(todoItem);
            });
    }
    
    public boolean deleteTodoItem(Long id) {
        if (todoItemRepository.existsById(id)) {
            todoItemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
