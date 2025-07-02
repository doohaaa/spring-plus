package org.example.expert.domain.todo.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.dto.response.TodoSearchResponse;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.example.expert.domain.comment.entity.QComment.comment;
import static org.example.expert.domain.manager.entity.QManager.manager;
import static org.example.expert.domain.todo.entity.QTodo.todo;
import static org.example.expert.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class TodoCustomRepositoryImpl implements TodoCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Todo> findByIdWithUser(Long id) {
        return Optional.ofNullable(
                queryFactory.selectFrom(todo)
                        .leftJoin(todo.user, user).fetchJoin()
                        .where(todo.id.eq(id))
                        .fetchOne()
        );
    }

    @Override
    public Page<TodoSearchResponse> findByTitleStartAtNickname(
            String title, LocalDateTime startAt, LocalDateTime endAt, String nickname, Pageable pageable) {

        BooleanExpression titleCond = title != null ? todo.title.containsIgnoreCase(title) : null;
        BooleanExpression createdAtCond = startAt != null && endAt != null ? todo.createdAt.between(startAt, endAt) : null;
        BooleanExpression nicknameCond = nickname != null ? todo.user.nickname.containsIgnoreCase(nickname) : null;

        List<TodoSearchResponse> results = (
                queryFactory.select(Projections.constructor(
                                TodoSearchResponse.class,
                                todo.id,
                                todo.title,
                                manager.countDistinct(),
                                comment.countDistinct())
                        )
                        .from(todo)
                        .leftJoin(todo.managers, manager)
                        .leftJoin(todo.user, user)
                        .leftJoin(todo.comments, comment)
                        .where(
                                titleCond, createdAtCond, nicknameCond
                        )
                        .offset(pageable.getOffset()) // 몇번째 페이지
                        .limit(pageable.getPageSize()) // 한페이지당 몇개
                        .groupBy(todo.id)
                        .orderBy(todo.createdAt.desc())
                        .fetch()

        );

        long total = Optional.ofNullable(queryFactory
                .select(todo.count())
                .from(todo)
                .where(
                        titleCond, createdAtCond, nicknameCond
                )
                .fetchOne()).orElse(0L);


        return new PageImpl<>(results, pageable, total);
    }


}
