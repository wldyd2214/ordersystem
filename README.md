## 주문 관리 시스템

- 주문 관리 시스템을 설계하고, 외부 시스템과의 데이터 연동을 위한 인터페이스를 구현하세요. 
- 이 시스템은 외부에서 주문 데이터를 가져와 저장하고, 내부 데이터를 외부로 전송하는 기능을 제공합니다.

---

### Description

- `주문 관리 시스템`을 구현합니다.
- 외부 시스템으로 부터 주문 데이터를 가져와 시스템의 메모리에 저장하여야 합니다.
- 메모리에 저장된 주문 데이터 조회할 수 있어야 합니다.
    - 주문 리스트 정보를 조회하여야 합니다.
    - 주문 ID를 통해 주문 정보를 조회할 수 있어야 합니다. 
- 내부 시스템에 저장된 주문 데이터를 외부 시스템으로 전송할 수 있어야 합니다.

---

### Requirements

- 설계한 인터페이스를 기반으로 주문 데이터를 외부 시스템에서 가져와 저장하고, 내부 데이터를 외부 시스템으로 전송하는 기능을 구현합니다.
- 데이터는 메모리에 저장하는 방식으로 처리하며, 별도의 데이터베이스 연동은 필요하지 않습니다. 

---

### API Specs

1️⃣ 주문 정보 저장 API (POST /orders)
- 외부 시스템으로부터 주문 데이터를 가져와 저장합니다.
- 주문은 주문 ID, 고객 명, 주문 날짜, 주문 상태(처리 중, 배송 중, 완료) 등의 속성을 가져야합니다.

2️⃣ 주문 상세 조회 API (GET /orders/{orderId})
- 주문 ID를 통해 주문 정보를 조회합니다.

3️⃣ 주문 목록 조회 API (GET /orders)
- 내부 시스템에 저장 되어있는 주문 리스트 정보를 조회합니다.

4️⃣ 주문 정보 전송 API (POST /orders/export)
- 주문 ID를 기반으로 주문 정보를 외부 시스템으로 전송합니다.

---

### 기술 스택
- Java 17 버전을 기반으로 개발하였으며 Spring Boot 버전은 3.2.11 사용하였습니다. 
- 아키텍처: 레이어드 + 클린 아키텍처를 적용한 패키지를 구성하여 계층간 요청과 응답 DTO를 분리하여 문제가 전파되는 부분을 최소화 하였습니다. 
- 방법론: DDD, TDD 방법론을 적용하여 신뢰성 있는 코드와 유지 보수가 용이한 애플리케이션을 구성하였습니다.
- Junit5 라이브러리를 활용하여 테스트 코드를 작성하였습니다.
- Swagger 라이브러리를 활용하여 연동규격서를 작성하고 테스트 툴을 제공하였습니다. (프로젝트 기동 후 http://localhost:8080/swagger-ui/index.html 접속)
- 외부 연동시 HTTP 연동은 RestTemplate을 사용하여 개발하였습니다.

---

### 프로젝트 실행
- DB는 따로 연동할 필요없이 애플리케이션 메모리 객체를 통해 데이터를 저장히기 때문에 연결하실 필요가 없습니다.
- 기타 환경 파일을 수정하거나 추가하실 필요 없습니다.
- 인텔리제이, 이클립스와 같은 툴이 있는 경우 OrdersystemApplication 파일 run 실행
- 8080 port로 기동됩니다.

---

### 패키지 구조
- 도메인 주도 개발 방식으로 패키지가 구성 되어있습니다.
    - presentation: Controller로 외부에서 요청을 받는 소스들이 존재합니다.
        - external 패키지: 외부 서비스 API가 제공되는 Controller가 존재하는 패키지입니다.
        - order 패키지: 실제 내부 서비스의 API가 제공되는 Controller가 존재하는 패키지입니다.
        - temp 패키지: 기능 구현시 주문 데이터를 저장하고 테스트 하기위해 테스트 편의용으로 만든 컨트롤러가 존재하는 패키지 입니다.
    - application: Facade 즉 특정 행위를 하기 위한 각각의 Service 메소드를 호출하는 역할을 하는 로직이 존재합니다.
    - domain: Service으로 도메인 로직이 포함 되어있습니다.
    - infrastucture: 외부 시스템 연동, 내부 데이터 저장 객체 테이블 모듈과 같은 외부 연동 모듈이 존재하는 구간입니다.
    - Mapper: 각 레이어별로 Mapper들이 존재하며 각 레이어들간 dto 변환을 위한 기능을 담당합니다.
        - 맵스트럭트 라이브러리를 사용하여 객체간의 변환 기능을 구현하였습니다.
    - test: 테스트 코드들이 존재하는 패키지 입니다.
        - xxxUnitTest: 단위 테스트를 작성한 테스트 클래스 입니다.
        - xxxIntegrationTest: 통합 테스트를 작성한 테스트 클래스 입니다.

---

### 클래스 다이어그램
![class](https://github.com/user-attachments/assets/33871bac-266b-48eb-bf88-bc4ed0074fad)

