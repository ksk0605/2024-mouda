# 🤝 모여봐요, 우리의 다락방 (모우다)

# 🙋‍♂️ 내가 구현한 핵심 기능

## 🗂️ 목차
1. [인증/인가](#-1-인증인가)
2. [로깅 AOP 도입](#-2-로깅-aop-도입)
3. [추첨 도메인 개발](#-3-추첨-도메인-개발)
4. [아키텍처 리팩토링 주도](#-4-아키텍처-리팩토링-주도)
5. [테스트 코드 자동화 환경 구축](#-5-테스트-코드-자동화-환경-구축)

---

## 🔐 1. 인증/인가

JWT를 이용한 인증/인가 시스템을 구현하고, 로그인 사용자를 추출하는 `ArgumentResolver`를 도입했습니다.  
Interceptor로 요청 전 토큰을 검증하고, 이후 컨트롤러 단에서 유저 정보를 주입받도록 구성했습니다.

📁 주요 코드
- [`LoginMemberArgumentResolver.java`](https://github.com/ksk0605/2024-mouda/blob/develop/backend/src/main/java/mouda/backend/common/config/argumentresolver/LoginMemberArgumentResolver.java)
- [`AuthenticationCheckInterceptor.java`](https://github.com/ksk0605/2024-mouda/blob/develop/backend/src/main/java/mouda/backend/common/config/interceptor/AuthenticationCheckInterceptor.java)
- [`AccessTokenProvider.java`](https://github.com/ksk0605/2024-mouda/blob/develop/backend/src/main/java/mouda/backend/auth/implement/jwt/AccessTokenProvider.java)

📘 **배운 점**
- 인증 로직을 공통화하고, 스프링 기술을 기반으로 책임을 분리하는 경험을 했습니다.

---

## 📌 2. 로깅 AOP 도입

요청 및 응답 정보를 로깅하기 위한 기능을 처음에는 Interceptor로 구현했으나, afterCompletion 으로 구현된 요청 로깅으로 인해 백엔드 로직 중 예외 발생시에는 로깅이 되지 않는 문제가 있었습니다. 이를 해결하기 위해 AOP 기반으로 리팩토링했습니다.


🔗 관련 PR: [#427 - 요청 로깅 기능에서 예외 발생시 로깅 누락 이슈 해결](https://github.com/woowacourse-teams/2024-mouda/pull/427)

📁 주요 코드
- [`RequestLoggingAspect.java`](https://github.com/ksk0605/2024-mouda/blob/develop/backend/src/main/java/mouda/backend/aop/logging/RequestLoggingAspect.java)

📘 **배운 점**
- AOP를 통해 비즈니스 로직과 부가 기능을 분리하는 방법을 익혔습니다.

---

## 🎯 3. 추첨 도메인 개발

선착순/랜덤 기반 모임 참여 기능을 도메인 중심, TDD로 개발했습니다.  
유저가 지정된 시간 내에 모임에 신청하면, 서버에서 참여자를 추첨해주는 로직을 구현했습니다.

📁 주요 코드
- [`Bet(추첨 도메인) 모듈 모음`](https://github.com/ksk0605/2024-mouda/tree/develop/backend/src/main/java/mouda/backend/bet)
- [`Bet(추첨 도메인) 테스트 코드 모음`](https://github.com/ksk0605/2024-mouda/tree/develop/backend/src/test/java/mouda/backend/bet)

📘 **배운 점**
- 시간 기반 트리거(스케줄러)를 통한 도메인 이벤트 흐름을 설계하는 경험

---

## 🧱 4. 아키텍처 리팩토링

서비스 로직이 Service 클래스에 집중되면서 테스트가 어려워졌습니다.  
도메인 규칙을 분리한 Implement 계층을 도입해 관심사를 나누고, 도메인 중심 구조로 개선했습니다.

🔗 구조개선 가이드를 위해 작성한 PR: [#527 - 패키지 구조 개선 및 리팩토링 예시 코드 작성](https://github.com/woowacourse-teams/2024-mouda/pull/527)

📁 구조 변화
- Before: `MoimService.java` → 로직 혼합
- After:  
  - `MoimService.java` → 흐름 제어  
  - `MoimFinder.java` → 도메인 규칙 위임

📘 **배운 점**
- 책임 분리를 통해 테스트 커버리지가 향상되었고, 유지보수가 쉬워졌습니다.
- 서비스 계층의 역할을 명확히 정의할 수 있는 구조적 시야가 생겼습니다.

---

## 🧪 5. 테스트 코드 자동화 환경 구축

초기에는 매 테스트마다 수동으로 DB 초기화를 해줘야 했고, 종종 누락되는 문제가 있었습니다.  
이를 해결하기 위해 JUnit 5 Extension을 적용하고, 자동 실행되도록 Auto Detection 설정을 추가했습니다.

🔗 관련 PR: [#372 - 데이터베이스 클리너 로직 개선](https://github.com/woowacourse-teams/2024-mouda/pull/372)

📁 주요 코드
- [`DatabaseCleaner.java`](https://github.com/ksk0605/2024-mouda/blob/develop/backend/src/test/java/mouda/backend/common/config/DatabaseCleaner.java)

📘 **배운 점**
- 테스트 안정성 자체가 시스템 품질에 중요한 요소임을 체감했습니다.
- JUnit의 확장성과 Spring 테스트 라이프사이클에 대한 이해가 깊어졌습니다.

---

# 🛠 모우다 인프라 아키텍처

![image](https://github.com/user-attachments/assets/e3dfbd96-1009-42d0-8647-347b410a7806)

# 프로젝트 소개
![1](https://github.com/user-attachments/assets/de440b71-0bdf-48cc-a222-f528be4dadb7)
![2](https://github.com/user-attachments/assets/c84f1d0b-f859-41e3-8da1-144bde6c8b55)
![3](https://github.com/user-attachments/assets/966ba62d-1fb8-4b14-99d5-9777445427ab)
![4](https://github.com/user-attachments/assets/57d69983-0046-450f-9f00-759402ab1597)
![5](https://github.com/user-attachments/assets/e04dace0-6c19-43bd-b6c9-d5d7f6e14018)
![6](https://github.com/user-attachments/assets/0da68d63-9f91-44f8-ab2e-fb255c076f00)
![7](https://github.com/user-attachments/assets/e2789931-da9a-4242-93a7-35b569833519)

# 🙋‍♀️ 모우다 팀원 소개

| ![상돌](https://github.com/user-attachments/assets/9817062f-6213-47fb-94b2-77dbd08b9848) | ![안나](https://github.com/user-attachments/assets/83d147df-9b80-4703-aa66-3632da8e9ba4) | ![테니](https://github.com/user-attachments/assets/cf57b0b3-3a93-4f6e-8bac-8ab65261594c) | ![테바](https://github.com/user-attachments/assets/09151d0f-7f5d-4a3f-9c89-7c8e15abbd14) | ![호기](https://github.com/user-attachments/assets/276888b2-aae7-48bf-8e0e-31b7585f2e51) | ![소파](https://github.com/user-attachments/assets/96a04e69-ffce-411d-ad94-a5c1bbe27b5f) | ![수야](https://github.com/user-attachments/assets/b4427e5c-0d8a-467c-a2dd-137a4b5aecce) | ![치코](https://github.com/user-attachments/assets/76b25466-ab62-4e91-8b84-3139f8be8b71) |
|:----:|:----:|:----:|:----:|:----:|:----:|:----:|:----:|
| **BE** | **BE** | **BE** | **BE** | **BE** | **FE** | **FE** | **FE** |
| [상돌](https://github.com/pricelees) | [안나](https://github.com/Mingyum-Kim) | [테니](https://github.com/ay-eonii) | [테바](https://github.com/ksk0605) | [호기](https://github.com/hoyeonyy) | [소파](https://github.com/ss0526100) | [수야](https://github.com/cys4585) | [치코](https://github.com/jaeml06) |
