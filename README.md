# 3PL Voc Handling Application

### 1. 개요: 제삼자물류 시스템에서 VOC가 발생하였을 때 이를 처리하기 위한 백엔드 로직을 구현한 어플리케이션이다.

### 2. 프로젝트 기간
- 1차: 2023.05.23 - 2023.05.29 (7일)
- 리팩토링 및 기능 추가(2023.06 - 2023.07)
### 3. 개발 방법론
- MVP(Minimum Variable Product)로 최소한의 필요한 기능만을 구현하고자 함
- 개발 환경: `IntelliJ 2022.3`, `MacOS Monterey`
- 간단한 뷰 구성을 위해 SSR 방식 사용 (Rest 방식의 Controller 및 관련 코드는 구분을 위해 주석 처리)

### 4. 주요 기능
- 운송사, 고객사 `Entity` 생성 및 연관관계 설정
- `Voc` 및 관련 `Value Objects`, `Entity` 생성 및 연관관계 설정
- `Claim` 조회 기능
- `Voc` 생성 및 조회 기능
- `Voc` 발생에 따른 귀책 당사자 `Penalty` 처리 기능
  - 운송사 귀책시 월급 공제
- `Voc` 발생에 따른 `Compensation` 요구 처리 기능
  - 고객사 요구시 임의의 `payment` 지급 기능
- 운송 기사 마이페이지를 통한 `Voc` 알림 기능
  - `SSE(Server-Sent Event)`를 이용한 구독->알림 로직 구현

### 5. 시나리오
- 임의의 사용자로부터 `Claim`이 접수된다(`Claim`은 더미 데이터로서 생성한다).
- 관리자는 접수된 `Claim`을 확인하고 `Voc`를 생성한다.
  - `Voc` 생성시 귀책 당사자를 설정한다. 귀책 당사자는 운송사 혹은 고객사이다. 운송사의 경우 실질 귀책 당사자는 배송기사로 설정한다.
  - 현재 프로젝트에서는 귀책 당사자는 전부 운송사이다.
  - 귀책 당사자, 즉 배송기사에게는 `Penalty` 금액이 부과된다. `Penalty`는 배송기사의 월급에서 금액을 제외하는 것으로 처리된다.
  - 고객사에게는 `Compensation` 금액이 지급된다.
- 생성된 `Voc`는 운송기사 마이페이지로 전송된다. 알림이 지원된다.
- 배송기사는 `Voc`를 확인하고 두 가지 액션 중 한가지를 선택해 응답해야 한다
  - 승낙: 해당 `Voc`에 업데이트 되며 `Penalty` 부과와 `Compensation` 지급후 `DB` 저장으로 마무리 된다.
  - 거절: 배송기사는 사유와 함께 거절할 수 있다. `Penalty`와 `Compensation`은 처리되지 않고 홀딩된다. 정책에 따른 별도 로직이 요구된다.

### 6. 기술 스택
- `Java` JDK 11
- `Gradle` v.7.6.1
- `SpringBoot` v2.7.12
- `Spring Data Jpa` v2.7.12
- `MySql` v8.0.33
- `Testcontainers` v1.18.1
- `JUnit` v5.8.2
- `Mockito` v4.5.1
- `Docker` v20.10.22
- `Docker-Compose` v3.8
- `Thymeleaf` v3.0.15

### 7. 설계 및 구조
- ERD
  ![ERD](docs/erd/erd.png)
- Class Diagram
  ![CLASS_DIAGRAM](docs/diagrams/class-diagram.png)


### 8. 특이 사항 (java docs)

<details>
<summary>
v.1.0.0 
</summary>

#### 1. `VocService.registerVoc()`
![ERD](docs/java-docs/VocService.registerVoc%20.png)
#### 2. `VocService.handleDriverPenalty()`
![ERD](docs/java-docs/VocService.handleDriverPenalty.png)
#### 3. `NotificationService`
![ERD](docs/java-docs/NotificationService.png)
#### 4. `NotificationService.emitter`
![ERD](docs/java-docs/NotificationService.emitter.png)
#### 5. `DeliverDriver.paySalary()`
![ERD](docs/java-docs/DeliverDriver.paySalary.png)
#### 6. `VocDto.from()`
![ERD](docs/java-docs/VocDto.from.png)
#### 7. `VocController.getCompensations()`
![ERD](docs/java-docs/VocController.getCompensations.png)
#### 8. `NotificationController.subscribeToNotifications()`
![ERD](docs/java-docs/NotificationController.subscribeToNotifications.png)
#### 9. `NotificationController.processHeartbeat()`
![ERD](docs/java-docs/NotificationController.processHeartbeat.png)
#### 10. `VocControllerTest.registerVoc_Success()`
![ERD](docs/java-docs/VocControllerTest.registerVoc_Success.png)

</details>


<details>
<summary>
v.1.3.0 
</summary>

다음과 같은 문제를 해결하기 위해 도메인 주도 개발 방법론(DDD)를 적용한 아키텍처 수정 업데이트를 진행한 버전이다.
자세한 내용은 밑의 버전 업데이트 노트에서 기술한다. 

기존의 문제 사항
1) 컨트롤러와 서비스 간의 의존관계 문제
2) 서비스와 서비스 간의 많은 참조 관계 문제
3) 서비스의 부가 기능 처리 문제

아키텍처 변경 내용 
![img_2.png](docs/java-docs/img_2.png)
</details>

### 9. 시연 영상 및 테스트 코드 통과 현황
- 시연 영상
  - 클레임, Voc, 배상 조회
    ![views](docs/demo/views.gif)
  - Voc 등록
    ![views](docs/demo/voc-register.gif)
  - 운송기사 마이페이지
    ![views](docs/demo/driver-mypage.gif)
  - Voc 등록시 알람
    ![views](docs/demo/alert.gif)
- 테스트 코드
  ![ERD](docs/demo/test1.png)
  ![ERD](docs/demo/test2.png)




### 10. 구동 방법
- 필요한 환경 변수는 다음과 같다. `.env` 파일을 통해 docker 빌드시 db의 root 계정을 생성해주고 어플리케이션 구동시 주입해준다.
  - `{SPRING_DATA_SOURCE_USERNAME}`
  - `{SPRING_DATA_SOURCE_PASSWORD}`
  - `.env` 파일 예시 <br>
    `SPRING_DATASOURCE_USERNAME=root
    SPRING_DATASOURCE_PASSWORD=1234`
- docker-compose 파일을 실행해 컨테이너에 db를 띄워준다. <br>
  `docker-compose -f docker-compose-local.yml up`


  --- 

## update note

### v.1.1.0
#### 2023.06.02

- 카프카를 이용한 비동기 메시지 큐 구현
  - 의존성 구성 방식: `Docker-compose` 실행시 컨테이너에 `Kafka`와 `Zookeeper`를 함께 생성, `configure` 클래스에서 `bean` 등록
  - 작동 방식:
    - 새로운 `Voc`가 생성되면 `KafkaProducerService`의 `notifyNewVoc()`를 통해 등록 메시지가 `message queue`로 전달된다. `KafkaConsumerService`의 `handleNewVocNotification()` 메서드는 `new-voc` 토픽을 구독하여 새로운 `Voc` 정보를 받아본다.
    - 기존 notification alert 방식인 Sse Emitter는 유지하며, 기존 코드의 큰 수정 삭제 없이 kafka 아키텍처를 구성
  - 의의:
    - 현재 코드에서는 단순히 업데이트에 대한 `notification` 용도로 유의미한 데이터를 전달하지는 않는다.
    - 추후 `app push` 기능을 추가할 수 있는 구성적 기반을 마련하는 데 의의가 있다.


### v.1.2.0
#### 2023.06.25
- entity 식별 토큰 추가
  - 배경:
    - `DBMS`의 `Entity` 개념에서 고유한 식별자는 중요한 개념이다. `Entity`의 생명주기에서 형태와 내용은 바뀔 수 있지만 연속성을 유지하기 위해서는 식별자는 유일하고 고유해야 한다.
    - 일반적으로 `DBMS`는 `자동 증가 속성(AUTO_INCREMENT)`을 통해 PK 값을 증가시킬 수 있는 기능을 제공하며, JPA를 사용하는 프로젝트에서 `@GeneratedValue` 애노테이션을 PK 자동 생성 기능을 활용한다.
  - 기존 방식의 문제:
    - 그러나 이렇게 생성된 pk로서의 id는 식별자라는 기능에는 충실하지만 부수적인 문제로서 보안, 비밀유지 측면에서 문제 소지 가능성을 갖는다.
    - 예를 들어, 본 프로젝트에서는 운전기사가 마이페이지에 접속할 때 `DeliveryDriver`의 `id`에 대해 `GET` 요청을 통해 `my-page`에 접근하는 시나리오를 설정했다.
    - 이 경우 `drivery id`는 순번으로서 생성되기 때문에 임의의 숫자를 입력하여 타인의 `my-page`에 대한 접속 루트가 열리는 문제가 생긴다. 또한 경쟁사의 악의적인 시도로 특정 연속하는 순서를 요청해 당사의 운전기사 수를 유추할 수 있게 된다는 문제가 생긴다.
    - 이처럼 `DBMS`에 고유하게 존재하는 pk 값이 인터페이스 레벨까지 올라와 버리면 내부 구조에 대한 노출로 인한 문제점을 야기할 수 있다.
  - 대체키의 사용
    - 위의 문제점을 해결하는 방법으로 대체키를 도입한 것이 해당 버전 리팩토링의 주요 사항이다.
    - 세부 사항
      - 기존 `GET` 요청 `URI` -> `http://localhost:54380/delivery-driver/v1/my-page?id=1`
      - 변경 `GET` 요청 `URI` -> `http://localhost:54380/delivery-driver/v2/my-page?token=ojH1C3ilyZb8Pmd`
      - 토큰은 랜덤 알파벳으로 조합된다. UUID를 사용할 수도 있었지만 알파벳 생성을 위해 `apache commons`의 `lang 라이브러리`에서 제공하는 랜덤 문자열 생성 방식을 사용하였다.
      - 외부 접촉이 가능한 다른 엔티티들에 대해서도 token 속성을 추가하였다.
      - 구현 코드는 다음과 같다.
      ```java
      public class DeliveryDriver extends BaseEntity {

         private static final String DELIVERY_DRIVER_PREFIX = "deliveryDriver_";

         //...

         private String deliveryDriverToken;

         // ...

         private void generateToken() {
             this.deliveryDriverToken = TokenGenerator.randomCharacterWithPrefix(DELIVERY_DRIVER_PREFIX);
         }
      }
      
      public class TokenGenerator {
         private static final int TOKEN_LENGTH = 30;

         public static String randomCharacter(int length) {
            return RandomStringUtils.randomAlphanumeric(length);
         }

         public static String randomCharacterWithPrefix(String prefix) {
             return prefix + randomCharacter(TOKEN_LENGTH - prefix.length());
         }
      }


### v.1.3.0
#### 2023.07.13
1) 레이어 계층 구조 변경 
- 기존 3티어 레이어드 아키텍처 -> 변경 도메인 중심 4계층 아키텍처 <br>

![img.png](docs/java-docs/img.png)
- 각 레이어 구분 및 역할 상세<br>

![img_1.png](docs/java-docs/img_1.png)
<br>
<br>

2) Application 계층 생성
- transaction으로 묶여야 하는 도메인 로직과 기타 부수적인 기능을 수행하는 계층으로 정의한다.
- 일반적인 도메인 주도 설계에서 이야기하는 역할, 즉 수행할 작업을 정의하고 작업을 조정하는 역할은 사실 도메인의 역할과 비슷하다.
- 따라서 실용적인 측면에서 서비스 간의 조합을 하나의 요구사항에서 처리해야 할 때 필요한 작업을 수행하는 정도로 정의한다.
- 예를 들어, Voc가 등록된 뒤 추가적으로 필요한 Claim에 대한 처리, Voc 발생에 대한 알림 처리 등을 수행한다.
- 인터페이스 aggregation의 의미로 사용되는 Facade로 클래스를 네이밍한다.
-> 서비스 간 참조 관계를 해제하여 계층 간의 구조를 명확히 한다. 

3) Info 객체 도입 
- 기존 Dto 및 Response 객체로 통용되던 Vo를 Info 객체로 통일한다.
- 도메인 계층 이상으로 리턴되는 값들에 대한 일관성 있는 Vo 역할이 가능하다.

4) 도메인 계층 클래스의 네이밍 세분화
- 주요 도메인의 흐름을 관리하는 핵심 Service를 두고, 이를 위한 Support 수준의 클래스들은 기능에 따라 Service 이외의 네이밍으로 한다.
- 예를 들어 Reader, Persister, Factory 등등이다.
- 해당 클래스들은 도메인에서 추상화 레벨로 존재하고, 인프라 계층에서 구현체로서 존재하도록 한다.

5) 추상화 레벨 강화
- 도메인 레이어의 추상화 레벨 강화하였다.
- 기존 데이터베이스 접근 로직 추상화 -> 도메인 레벨의 기능별 분리에 따른 변화와 함께 모든 클래스를 추상화하여 표현하며 세부 구현체들은 인프라 계층에서 구현하도록 한다.

---
