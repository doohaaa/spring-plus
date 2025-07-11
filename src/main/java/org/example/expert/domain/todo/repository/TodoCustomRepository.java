package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.dto.response.TodoSearchResponse;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TodoCustomRepository {
    Optional<Todo> findByIdWithUser(Long id);
    Page<TodoSearchResponse> findByTitleStartAtNickname(
            String title, LocalDateTime startAt, LocalDateTime endAt, String nickname, Pageable pageable);}
