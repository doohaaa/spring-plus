package org.example.expert.domain.todo.repository;

import org.example.expert.config.QueryDslConfig;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.enums.UserRole;
import org.example.expert.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Import(QueryDslConfig.class)
class TodoCustomRepositoryImplTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoRepository todoRepository;

    private User user;
    private Todo todo;

    @BeforeEach
    void setUp(){
        user = new User("hello@ecample.com", "password", UserRole.USER, "nickname");
        userRepository.save(user);

        todo = new Todo("what to do", "spring-plus task", "sunny",user );
        todoRepository.save(todo);
    }

    @Test
    @DisplayName("QueryDsl 을 사용했을때 findByIdWithUser가 제대로 작동하는지")
    void findByIdWithUser() {
        // given
        Long todoId = todo.getId();
        // when
        Todo todo = todoRepository.findByIdWithUser(todoId).orElseThrow();
        // then
        assertThat(todo).isNotNull();
    }
}