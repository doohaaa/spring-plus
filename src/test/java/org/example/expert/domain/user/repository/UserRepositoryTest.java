package org.example.expert.domain.user.repository;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.example.expert.config.QueryDslConfig;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.enums.UserRole;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
@Import(QueryDslConfig.class)
class UserRepositoryTest {

    // 로그 지워
    @BeforeAll
    static void turnOffHibernateLogging() {
        Logger sqlLogger = (Logger) LoggerFactory.getLogger("org.hibernate.SQL");
        sqlLogger.setLevel(Level.OFF);

        Logger binderLogger = (Logger) LoggerFactory.getLogger("org.hibernate.type.descriptor.sql.BasicBinder");
        binderLogger.setLevel(Level.OFF);
    }

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        String nickname = "kevin";

        // userRepository 에 100만건 데이터 생성
        for (int i = 0; i < 1000000; i++) {
            userRepository.save(new User("email" + i + "@test.com", "Password" + i + "!", UserRole.USER,
                    UUID.randomUUID().toString().replace("~", "").substring(0, 5)));
        }

        userRepository.save(new User("email11000000@test.com", "Password1!", UserRole.USER, nickname));
        userRepository.save(new User("email12000000@test.com", "Password2!", UserRole.USER, nickname));
        userRepository.save(new User("email13000000@test.com", "Password3!", UserRole.USER, nickname));
        userRepository.save(new User("email14000000@test.com", "Password4!", UserRole.USER, nickname));

    }

    @Test
    @DisplayName("일치하는 Nickname을 가진 User 찾기")
    void findbyNickname() {
        // given
        long beforeTime = System.currentTimeMillis();
        String nickname = "kevin";

        // when
        List<User> users = userRepository.findbyNickname(nickname);

        // then
        assertThat(users.get(0).getNickname()).isEqualTo(nickname);
        long afterTime = System.currentTimeMillis();
        long diff = afterTime - beforeTime;

        System.out.println("-------------------------\n" +
                "100만건 로그 O, test 메서드 실행 시간: " + diff + " ms \n");

    }
}