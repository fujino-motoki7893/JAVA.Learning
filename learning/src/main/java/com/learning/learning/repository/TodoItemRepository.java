package com.learning.learning.repository;

import com.learning.learning.entity.TodoItem;
import org.springframework.data  .jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    
    List<TodoItem> findByCompleted(Boolean completed);
    
    @Query("SELECT t FROM TodoItem t WHERE t.title LIKE %?1%")
    List<TodoItem> findByTitleContaining(String title);
    
    List<TodoItem> findByOrderByCreatedAtDesc();
}
