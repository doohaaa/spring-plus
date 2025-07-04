# 💫 코드 개선, 성능 최적화 개인 프로젝트

## 🎀 소개

기존 코드 분석 및 개선 방안 모색, 성능 최적화를 위한 개인 프로젝트입니다.

> **개발기간 : 2025-06-23 ~ 2025-07-04**

## ✨ 활용한 주요 개념

- JPA, JPQL, QueryDSL - 데이터베이스에 접근하는 다양한 방법에 대해 공부
- Test Code (Controller test, Repository test) - Service test 에서 더 나아간 상태기반 테스트에 대한 이해
- AOP - AOP 활용해 로그 기록
- @Transactional - 로깅 분리해 주요 트랜잭션 결과와 상관없이 요청 로그 저장하도록 보장
- Spring Security - JWT 토큰 기반의 프로그램에 Spring Security 를 도입
- 대용량 데이터 처리 - 테스트 코드로 대용량 데이터 주입해보기

## ✨운영 환경 변수

```
DATABASE=yourschemaname
DB_USERNAME=yourusername
DB_PASSWORD=youruserpassword
SECRET_KEY=your_jwt_secret_key
```

## 🪄 배운점

- Test code 작성시 어려움
    - 상태기반 / 행위기반 테스트가 있음을 인지 -> 종류에 따라 다른 구현법 학습
- Spring security 도입시 어려움
    - jwt 토큰을 사용한 인증 방식을 어떤식으로 수정해야하는지 학습, Context holder 관련 개념 학습
    - controller test code 작성시 테스트 코드도 변경해줘야 하는데 이 부분은 추가 학습 필요 !
- AOP 활용하는 방법
    - 이론상으로는 알고 있었지만 집접 구현해본적이 없어서 처음에 헤맸지만 구현 해보니 왜 분리하는게 좋은지 체감할 수 있었음
- QueryDSL 사용시 어려움
    - 새로운 custom repository 를 생성했지만 하나의 repository 로 합해주지 않아서 문제 발생 -> extends 를 사용해 원하는 것들을 한곳으로 모아줄 수 있음
    - SQL 문으로 시작을 안하고 바로 QueryDSL 로 작성했더니 쿼리에 문제가 있는건지 프로그램 다른 코드에 문제가 있는건지 찾기 어려움 -> 먼저 SQL 문을 작성해서 동작을 확인하고 변환 하는
      방향으로 구현

<hr>

### [ English version ]

# 💫 Code Improvement, Performance optimisation Personal Project

## 🎀 Introduction

A personal project focused on analysing existing code, fixing bugs, exploring improvement strategies, and optimising
performance.

> **Development Period : 23.06.25 ~ 04.07.25**

## ✨ Technologies & Concepts Used

- Java 17
- Spring Boot 3.5 - for application framework and dependency injection.


- Spring Data JPA - ORM and database access.
- QueryDSL - Type-safe, flexible database access.
- Spring Security Integration - Integrated Spring Security on top of existing JWT authentication, modifying the
  permission verification logic accordingly to leverage SecurityContextHolder for user details and roles across the
  application.
- AOP (Aspect-Oriented Programming) - For logging and handling cross-cutting concerns cleanly.
- DTO (Data Transfer Object) - For clean request and response data management.
- RESTful API Design - Using @RestController, @RequestMapping, etc.

## ✨ Code Improvements & Optimisation Techniques

- 🛠️ AOP Advice Adjusted to Meet Requirements
    - Fixed incorrect AOP configuration to ensure changeUserRole() executes **before** the target method, allow logging
      and
      validation logic to run as intended.
- ✅ N+1 Query Problem Resolution
    - Used **fetch join** in QueryDSL to eliminate N+1 select issues, improving performance and reducing redundant
      queries.
- 🔁 Transactional Logging with Propagation.REQUIRES_NEW
    - Ensured that request logs are always saved regardless of the outcome of the main transaction by separating logging
      into an **independent transaction** using @Transactional(propagation = REQUIRES_NEW) and AOP.

## ✨Environment variables

```
DATABASE=yourschemaname
DB_USERNAME=yourusername
DB_PASSWORD=youruserpassword
SECRET_KEY=your_jwt_secret_key
```

## ✨ Learning

- 💡 Understanding Testing Approaches
    - Gained awareness of state-based vs behaviour-based testing and how their implementation strategies differ.
- 💡 Challenges During Spring security Integration
    - Learned how to modify JWT-based authentication to work with Spring Security and how to retrieve authentication
      data using SecurityContextHolder.
    - Realised that controller test cases need to be adjusted accordingly - still requires further learning.
- 💡 AOP in Practice
    - Although familiar with the theory, implementing AOP for the first time clarified the practical benefits of
      separation of functions.
- 💡 Difficulties with QueryDSL
    - Initially faced issues due to not extending the custom repository interface, preventing correct combination of
      repository functionalities.
    - To begin with writing queries directly in QueryDSL, making debugging harder. Learned to veritfy logic with raw SQL
      first
      before converting to QueryDSL.
      

