package org.example.expert.aop.log.service;

import lombok.RequiredArgsConstructor;
import org.example.expert.aop.log.entity.Log;
import org.example.expert.aop.log.repository.LogRepository;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.todo.repository.TodoRepository;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    // 매니저 등록 요청시 로그 기록
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAskManagerLog(Long userId, Long todoId, Long managerId, LocalDateTime requestTime, String requestUrl, String methodName) {

        User user = userRepository.findById(userId).orElseThrow();
        Todo todo = todoRepository.findById(todoId).orElseThrow();

        Log log = Log.builder()
                .user(user)
                .todo(todo)
                .managerId(managerId)
                .requestUrl(requestUrl)
                .method(methodName)
                .createdAt(requestTime)
                .build();

        logRepository.save(log);
    }
}
