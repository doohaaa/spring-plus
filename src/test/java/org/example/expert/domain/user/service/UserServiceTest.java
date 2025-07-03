//package org.example.expert.domain.user.service;
//
//import org.example.expert.ExpertApplication;
//import org.example.expert.domain.user.dto.response.UserNickNameResponse;
//import org.example.expert.domain.user.entity.User;
//import org.example.expert.domain.user.enums.UserRole;
//import org.example.expert.domain.user.repository.UserRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(classes = ExpertApplication.class)
//@Transactional
//class UserServiceTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserService userService;
//
//
//    @Test
//    @DisplayName("일치하는 nickname을 가진 유저를 검색. 그 유저의 nickname 이 일치하는지")
//    void getUserByNickName() {
//
//        // given
//        String givenNickname = "kevin";
//
//        userRepository.save(new User("email1@test.com", "Password1!", UserRole.USER, givenNickname));
//        userRepository.save(new User("email2@test.com", "Password2!", UserRole.USER, givenNickname));
//
//        // userRepository 에 100만건 데이터 생성
//        for (int i = 2; i < 7; i++) {
//            userRepository.save(new User("email" + i + "@test.com", "Password" + i + "!", UserRole.USER,
//                    UUID.randomUUID().toString().replace("~", "").substring(0, 5)));
//        }
//
//        // when
//        List<UserNickNameResponse> result = userRepository.findbyNickname(givenNickname);
//
//        // then
//        assertThat(result.get(0).getNickname()).isEqualTo(givenNickname);
//
//        if (result.size() > 3) {
//            assertThat(result.get(2).getNickname()).isEqualTo(givenNickname);
//        }
//
//    }
//}