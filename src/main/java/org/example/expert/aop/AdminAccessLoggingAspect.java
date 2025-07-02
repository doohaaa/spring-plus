package org.example.expert.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.expert.aop.log.service.LogService;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.manager.dto.request.ManagerSaveRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Aspect   // aop 구현
@Component
@RequiredArgsConstructor
public class AdminAccessLoggingAspect {

    private final HttpServletRequest request;
    private final LogService logService;

    @Before("execution(* org.example.expert.domain.user.controller.UserAdminController.changeUserRole(..))")
    public void logBeforeChangeUserRole(JoinPoint joinPoint) {
        // token 에서 userId 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser user = (AuthUser) authentication.getPrincipal();
        Long userId = user.getId();

        String requestUrl = request.getRequestURI();
        LocalDateTime requestTime = LocalDateTime.now();

        log.info("Admin Access Log - User ID: {}, Request Time: {}, Request URL: {}, Method: {}",
                userId, requestTime, requestUrl, joinPoint.getSignature().getName());
    }

    @Before("execution(* org.example.expert.domain.manager.controller.ManagerController.saveManager(..))")
    public void logBeforeEnrollManager(JoinPoint joinPoint) {
        // token 에서 userId 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser user = (AuthUser) authentication.getPrincipal();
        Long userId = user.getId();

        // todo id를 url 에서 파싱
        Object[] args = joinPoint.getArgs();
        Long todoId = (Long) args[1];

        // managerId 를 args 에서 파싱
        Long managerId = -1L;
        if (args[2] instanceof ManagerSaveRequest request) {
            managerId = request.getManagerUserId();
        }


        String requestUrl = request.getRequestURI();
        LocalDateTime requestTime = LocalDateTime.now();

        logService.saveAskManagerLog(userId, todoId, managerId, requestTime, requestUrl, joinPoint.getSignature().getName());

        log.info("Manager Change Request Log - User id:{}, todo id :{}, Request Time: {}, Request URL: {}, Method: {}",
                userId, todoId, requestTime, requestUrl, joinPoint.getSignature().getName());
    }
}
