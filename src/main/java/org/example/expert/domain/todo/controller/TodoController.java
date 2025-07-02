package org.example.expert.domain.todo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.todo.dto.request.TodoSaveRequest;
import org.example.expert.domain.todo.dto.response.TodoResponse;
import org.example.expert.domain.todo.dto.response.TodoSaveResponse;
import org.example.expert.domain.todo.dto.response.TodoSearchResponse;
import org.example.expert.domain.todo.service.TodoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<TodoSaveResponse> saveTodo(
            @AuthenticationPrincipal AuthUser authUser, // Security ContextHolder에 저장된 인증된 사용자 정보를 접근하는 애너테이션
            @Valid @RequestBody TodoSaveRequest todoSaveRequest
    ) {
        return ResponseEntity.ok(todoService.saveTodo(authUser, todoSaveRequest));
    }

    @GetMapping("/todos")
    public ResponseEntity<Page<TodoResponse>> getTodos(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String weather,
            @RequestParam(required = false) LocalDateTime startAt,
            @RequestParam(required = false) LocalDateTime endAt) {
        return ResponseEntity.ok(todoService.getTodos(page, size, weather, startAt, endAt));
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<TodoResponse> getTodo(@PathVariable("todoId") long todoId) {
        TodoResponse todoResponse = todoService.getTodo(todoId);
        return ResponseEntity.ok(todoResponse);
    }

    // 일정 검색 기능 추가
    @GetMapping("/todos/search")
    public ResponseEntity<Page<TodoSearchResponse>> searchTodos(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) LocalDateTime startAt,
            @RequestParam(required = false) LocalDateTime endAt,
            @RequestParam(required = false) String nickname
    ) {
        return ResponseEntity.ok(todoService.searchTodos(page, size, title, startAt, endAt, nickname));
    }
}
