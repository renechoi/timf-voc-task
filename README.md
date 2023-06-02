# 3PL Voc Handling Application 

### 1. 개요: 제삼자물류 시스템에서 VOC가 발생하였을 때 이를 처리하기 위한 백엔드 로직을 구현한 어플리케이션이다. 

### 2. 프로젝트 기간 : 2023.05.23 - 2023.05.29 (7일)
### 3. 개발 방법론: 
- MVP(Minimum Variable Product)로 최소한의 필요한 기능만을 구현하고자 함
- 개발 환경: `IntelliJ 2022.3`, `MacOS Monterey`
- 간단한 뷰 구성을 위해 SSR 방식 사용 (Rest 방식의 Controller 및 관련 코드는 구분을 위해 주석 처리)  

### 4. 주요 기능 : 
- 운송사, 고객사 `Entity` 생성 및 연관관계 설정  
- `Voc` 및 관련 `Value Objects`, `Entity` 생성 및 연관관계 설정  
- `Claim` 조회 기능 
- `Voc` 생성 및 조회 기능 
- `Voc` 발생에 따른 귀책 당사자 `Penalty` 처리 기능 
  - 운송사 귀책시 월금 공제
- `Voc` 발생에 따른 `Compensation` 요구 처리 기능
  - 고객사 요구시 임의의 `payment` 지급 기능
- 운송 기사 마이페이지를 통한 `Voc` 알림 기능
  - `SSE(Server-Sent Event)`를 이용한 구독->알림 로직 구현

### 3. 기술 스택 
- `Java` v11
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

### 4. 설계 및 구조 
- ERD
![ERD](docs/erd/erd.png)
- Class Diagram
![CLASS_DIAGRAM](docs/diagrams/class-diagram.png)

  
### 5. 특이 사항 (java docs)

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


### 6. 시연 영상 및 테스트 코드 통과 현황 
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




### 7. 구동 방법
- 필요한 환경 변수는 다음과 같다. `.env` 파일을 통해 docker 빌드시 db의 root 계정을 생성해주고 어플리케이션 구동시 주입해준다.
  - `{SPRING_DATA_SOURCE_USERNAME}`
  - `{SPRING_DATA_SOURCE_PASSWORD}`
  - `.env` 파일 예시 <br>
    `SPRING_DATASOURCE_USERNAME=root
    SPRING_DATASOURCE_PASSWORD=1234`
- docker-compose 파일을 실행해 컨테이너에 db를 띄워준다. <br>
  `docker-compose -f docker-compose-local.yml up`


  --- 

## v.1.1.0 update note

- 카프카를 이용한 비동기 메시지 큐 구현
  - 의존성 구성 방식: `Docker-compose` 실행시 컨테이너에 `Kafka`와 `Zookeeper`를 함께 생성, `configure` 클래스에서 `bean` 등록
  - 작동 방식:
    - 새로운 `Voc`가 생성되면 `KafkaProducerService`의 `notifyNewVoc()`를 통해 등록 메시지가 `message queue`로 전달된다. `KafkaConsumerService`의 `handleNewVocNotification()` 메서드는 `new-voc` 토픽을 구독하여 새로운 `Voc` 정보를 받아본다.
    - 기존 notification alert 방식인 Sse Emitter는 유지하며, 기존 코드의 큰 수정 삭제 없이 kafka 아키텍처를 구성
  - 의의: 
    - 현재 코드에서는 단순히 업데이트에 대한 `notification` 용도로 유의미한 데이터를 전달하지는 않는다.
    - 추후 `app push` 기능을 추가할 수 있는 구성적 기반 마련한다.
