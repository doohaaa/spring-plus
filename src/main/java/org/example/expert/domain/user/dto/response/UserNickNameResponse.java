package org.example.expert.domain.user.dto.response;

import lombok.Getter;

@Getter
public class UserNickNameResponse {

    private final Long id;
    private final String nickname;

    public UserNickNameResponse(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
