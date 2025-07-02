package org.example.expert.domain.todo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoSearchResponse {

    Long todoId;
    String title;
    Long countManager;
    Long countComment;

}
