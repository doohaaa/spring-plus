package org.example.expert.aop.log.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.entity.User;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "log")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", nullable = false)
    private Todo todo;

    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "request_url", nullable = false)
    private String requestUrl;

    @Column(name = "method", nullable = false)
    private String method;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;


}
