package org.example.expert.domain.todo.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.dto.response.TodoSearchResponse;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.querydsl.core.types.ExpressionUtils.count;
import static com.querydsl.core.types.ExpressionUtils.predicate;
import static org.example.expert.domain.manager.entity.QManager.manager;
import static org.example.expert.domain.todo.entity.QTodo.todo;
import static org.example.expert.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class TodoCustomRepositoryImpl implements TodoCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Todo> findByIdWithUser(Long id){
        return Optional.ofNullable(
                queryFactory.selectFrom(todo)
                        .leftJoin(todo.user, user).fetchJoin()
                        .where(todo.id.eq(id))
                        .fetchOne()
        );
    }

    @Override
    public Page<TodoSearchResponse> findByTitleStartAtNickname(
            String title, LocalDateTime startAt, LocalDateTime endAt, String nickname, Pageable pageable){
        List<TodoSearchResponse> results =(
                queryFactory.select(Projections.constructor(TodoSearchResponse.class, todo.title, count(todo.managers), count(todo.comments)))
                        .from(todo)
                        .leftJoin(todo.user, user).fetchJoin()
                        .leftJoin(todo.managers, manager).fetchJoin()
                        .where(
                            title != null ? todo.title.containsIgnoreCase(title) : null,
                                startAt != null ? todo.createdAt.between(startAt, endAt) : null,
                                nickname != null ? todo.user.nickname.containsIgnoreCase(nickname) : null
                        )
                        .orderBy(todo.createdAt.desc())
                        .fetch()
        );
        long total = queryFactory
                .select(todo.count())
                .from(todo)
                .fetchOne();
        return new PageImpl<>(results, pageable, total);
    }

}
