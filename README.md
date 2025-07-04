# 💫 코드 개선, 성능 최적화 개인 프로젝트

<hr>

## 🎀 소개

기존 코드 분석 및 개선 방안 모색, 성능 최적화를 위한 개인 프로젝트입니다.

> **개발기간 : 2025-06-23 ~ 2025-07-04**

<hr>

## ✨ 활용한 주요 개념

- **@Transactional
- **JWT
- **JPA, JPQL, QueryDSL
- **Test Code (Controller test, Repository test)
- **AOP (Log)
- **N+1
- **Spring Security
- **대용량 데이터 처리

## ✨운영 환경 변수

```json
DATABASE=yourschemaname
DB_USERNAME=yourusername
DB_PASSWORD=youruserpassword
SECRET_KEY=your_jwt_secret_key
```

## 🪄 트러블 슈팅

- **Test code 작성시 어려움
    - **상태기반 / 행위기반 테스트가 있음을 인지 -> 종류에 따라 다른 구현법 학습
- **Spring security 도입시 어려움
    - **jwt 토큰을 사용한 인증 방식을 어떤식으로 수정해야하는지 학습, Context holder 관련 개념 학습
    - **controller test code 작성시 테스트 코드도 변경해줘야 하는데 이 부분은 추가 학습 필요 !
- **AOP 활용하는 방법
    - **이론상으로는 알고 있었지만 집접 구현해본적이 없어서 처음에 헤맸지만 구현 해보니 왜 분리하는게 좋은지 체감할 수 있었음
- **QueryDSL 사용시 어려움
    - **새로운 custom repository 를 생성했지만 하나의 repository 로 합해주지 않아서 문제 발생 -> extends 를 사용해 원하는 것들을 한곳으로 모아줄 수 있음
    - **SQL 문으로 시작을 안하고 바로 QueryDSL 로 작성했더니 쿼리에 문제가 있는건지 프로그램 다른 코드에 문제가 있는건지 찾기 어려움 -> 먼저 SQL 문을 작성해서 동작을 확인하고 변환 하는
      방향으로 구현