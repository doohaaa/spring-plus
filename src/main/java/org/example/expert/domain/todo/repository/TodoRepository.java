package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoCustomRepository {

    @Query("SELECT t from Todo t "+
    "where (:weather is null or t.weather=:weather)"+
    "and (:startAt is null or t.modifiedAt >= :startAt)"+
    "and (:endAt is null or t.modifiedAt<= :endAt)")
    Page<Todo> findAllByWeatherByModifiedAt(
            @Param("weather") String weather,
            @Param("startAt")LocalDateTime startAt,
            @Param("endAt") LocalDateTime endAt,
            Pageable pageable);
}
