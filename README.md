# 방탈출 예약 및 예약 관리 프로그램

## 어드민

- [x] `localhost:8080/admin` 요청 시 어드민 메인 페이지를 응답한다.
    - 어드민 메인 페이지는 `templates/admin/index.html` 파일을 이용한다.
- [x] `Member`의 `Role`이 `ADMIN`인 사람만 `/admin`으로 시작하는 페이지에 접근할 수 있다.
    - [x] HandlerInterceptor를 활용하여 권한을 확인한다.
    - [x] 권한이 없는 경우 요청에 대한 거부 응답을 한다.

### 어드민 예약 관리

- [x] `/admin/reservation` 요청 시 예약 관리 페이지를 응답한다.
    - 페이지는 `templates/admin/reservation-new.html` 파일을 이용한다.
    - 예약 관리 페이지 로드 시 예약 목록 조회 API가 호출된다.
- [x] 예약 목록 조회 API 명세
    - Request
        ```http request
        GET /admin/reservations HTTP/1.1
        ```
    - Response
        ```
        HTTP/1.1 200
        Content-Type: application/json
        
        [
            {
                "id": 1,
                "member": {
                    "id": 1,
                    "name": "브라운",
                    "email": "brown@email.com"
                }
                "theme": {
                    "id": 1,
                    "name": "레벨1 탈출",
                    "description": "우테코 레벨1을 탈출하는 내용입니다.",
                    "thumbnail": "https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg"
                }
                "date": "2023-01-01",
                "time": {
                    "id": 1,
                    "startAt": "10:00"
                }
            },
            {
                "id": 2,
                "member": {
                    "id": 1,
                    "name": "브라운",
                    "email": "brown@email.com"
                }
                "theme": {
                    "id": 1,
                    "name": "레벨1 탈출",
                    "description": "우테코 레벨1을 탈출하는 내용입니다.",
                    "thumbnail": "https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg"
                }
                "date": "2023-01-02",
                "time": {
                    "id": 1,
                    "startAt": "10:00"
                }
            }
        ]
        ```
- [x] 관리자는 사용자, 테마, 날짜, 예약시간을 선택하여 예약을 생성할 수 있다.
    - [x] 사용자 조회 API 명세
        - Request
            ```http request
            GET /admin/members HTTP/1.1
            ```
        - Response
            ```
            HTTP/1.1 200
            Content-Type: application/json
            
            [
                {
                    "id": 1,
                    "name": "어드민",
                    "email": "admin@email.com"
                },
                {
                    "id": 2,
                    "name": "브라운",
                    "email": "brown@email.com"
                }
            ]
            ```
    - [x] 예약 추가 API 명세
        - Request
            ```http request
            POST /admin/reservations HTTP/1.1
            content-type: application/json
            Headers: Cookie=JSESSIONID=89EA9B9B9F00EAC2B8D2208649EA6260
            host: localhost:8080
             
            {
                "memberId": 1
                "themeId": 1,
                "date": "2023-08-05",
                "timeId": 1,
            }
            ```
        - Response
            ```
            HTTP/1.1 201
            Content-Type: application/json
             
            {
                 "id": 1,
                 "member": {
                     "id": 1,
                     "name": "브라운",
                     "email": "brown@email.com"
                 }
                 "theme": {
                     "id": 1,
                     "name": "레벨1 탈출",
                     "description": "우테코 레벨1을 탈출하는 내용입니다.",
                     "thumbnail": "https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg"
                 }
                 "date": "2023-08-05",
                 "time": {
                     "id": 1,
                     "startAt": "10:00"
                 }
            }
            ```
- [x] 관리자는 예약을 취소할 수 있다.
    - [x] 예약 삭제 API 명세
        - Request
            ```http request
            DELETE /admin/reservations/1 HTTP/1.1
            ```
        - Response
            ```
            HTTP/1.1 204
            ```
- [x] 예약자별, 테마별, 날짜별 검색 조건을 사용해 예약 검색이 가능하다.


### 어드민 예약시간 관리

- [x] `/admin/time` 요청 시 예약시간 관리 페이지를 응답한다.
    - 페이지는 `templates/admin/time.html` 파일을 이용한다.
- [x] 예약시간 목록 조회 API 명세
    - Request
        ```http request
        GET /times HTTP/1.1
        ```
    - Response
        ```
        HTTP/1.1 200
        Content-Type: application/json
        
        [
            {
                "id": 1,
                "startAt": "10:00"
            },
            {
                "id": 2,
                "startAt": "12:00"
            }
        ]
        ```
- [x] 예약시간 추가 API 명세
    - Request
        ```http request
        POST /admin/times HTTP/1.1
        content-type: application/json
        
        {
            "startAt": "10:00"
        }
        ```
    - Response
        ```
        HTTP/1.1 201
        Content-Type: application/json
        
        {
            "id": 1,
            "startAt": "10:00"
        }
        ```
- [x] 예약시간 삭제 API 명세
    - Request
        ```http request
        DELETE /admin/times/1 HTTP/1.1
        ```
    - Response
        ```
        HTTP/1.1 204
        ```

### 어드민 테마 관리

- [x] `/admin/theme` 요청 시 테마 관리 페이지를 응답한다.
    - 페이지는 `templates/admin/theme.html` 파일을 이용한다.
- [x] 테마 목록 조회 API 명세
    - Request
        ```http request
        GET /themes HTTP/1.1
        ```
    - Response
        ```
        HTTP/1.1 200
        Content-Type: application/json
        
        [
            {
                "id": 1,
                "name": "레벨1 탈출",
                "description": "우테코 레벨1을 탈출하는 내용입니다.",
                "thumbnail": "https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg"
            },
            {
                "id": 2,
                "name": "레벨2 탈출",
                "description": "우테코 레벨2를 탈출하는 내용입니다.",
                "thumbnail": "https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg"
            }
        ]
        ```
- [x] 테마 추가 API 명세
    - Request
        ```http request
        POST /admin/themes HTTP/1.1
        content-type: application/json
        
        {
            "name": "레벨1 탈출",
            "description": "우테코 레벨1을 탈출하는 내용입니다.",
            "thumbnail": "https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg"
        }
        ```
    - Response
        ```
        HTTP/1.1 201
        Location: /themes/1
        Content-Type: application/json
        
        {
            "id": 1,
            "name": "레벨1 탈출",
            "description": "우테코 레벨1을 탈출하는 내용입니다.",
            "thumbnail": "https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg"
        }
        ```
- [x] 테마 삭제 API 명세
    - Request
        ```http request
        DELETE /admin/themes/1 HTTP/1.1
        ```
    - Response
        ```
        HTTP/1.1 204
        ```

### 어드민 예약 대기 관리

- [ ] `/admin/waiting` 요청 시 예약 대기 관리 페이지를 응답한다.
    - 페이지는 `templates/admin/waiting.html` 파일을 이용한다.
- [ ] 예약 대기 목록 조회 API 명세
    - Request
        ```http request
        GET /admin/waitings HTTP/1.1
        ```
    - Response
        ```
        HTTP/1.1 200
        Content-Type: application/json
        
        [
            {
                "id": 1,
                "member": {
                    "id": 1,
                    "name": "브라운",
                    "email": "brown@email.com"
                }
                "theme": {
                    "id": 1,
                    "name": "레벨1 탈출",
                    "description": "우테코 레벨1을 탈출하는 내용입니다.",
                    "thumbnail": "https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg"
                }
                "date": "2023-01-01",
                "time": {
                    "id": 1,
                    "startAt": "10:00"
                }
            },
            {
                "id": 2,
                "member": {
                    "id": 1,
                    "name": "브라운",
                    "email": "brown@email.com"
                }
                "theme": {
                    "id": 1,
                    "name": "레벨1 탈출",
                    "description": "우테코 레벨1을 탈출하는 내용입니다.",
                    "thumbnail": "https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg"
                }
                "date": "2023-01-02",
                "time": {
                    "id": 1,
                    "startAt": "10:00"
                }
            }
        ]
        ```
- [ ] 관리자는 예약 대기 요청을 거절할 수 있다.
    - [ ] 예약 대기 삭제 API 명세
        - Request
            ```http request
            DELETE /waitings/1 HTTP/1.1
            ```
        - Response
            ```
            HTTP/1.1 204
            ```
- [ ] 예약 취소 발생 시 예약 대기자가 있는 경우 예약 승인이 된다.
      
## 사용자

- 사용자는 아래의 정보를 가진다.
    - 모든 정보는 비어있을 수 없다.
    - name: 사용자 이름
        - 이름은 20자를 초과할 수 없다. 
    - email: 이메일
        - 이메일 주소는 {영소문자와_}@{영소문자와_}.{영소문자와_} 형식과 일치해야 한다.
        - 이메일은 50자를 초과할 수 없다.
    - password: 비밀번호
        - 비밀번호는 50자를 초과할 수 없다.
    - email을 로그인의 id로, password를 비밀번호로 사용한다.

### 로그인

- [x] 세션 로그인 기능을 구현한다.
- [x] `GET /login` 요청 시 로그인 폼이 있는 페이지를 응답한다.
    - 페이지는 `templates/login.html` 파일을 이용한다.
- [x] `POST /login` 요청 시 로그인 폼에 입력한 email, password 값을 body에 포함하여 요청한다.
    - 로그인 요청 API 명세
        - Request
            ```http request
            POST /login HTTP/1.1
            content-type: application/json
            host: localhost:8080
            
            {
                "email": "admin@email.com"
                "password": "password",
            }
            ```
        - Response
            ```
            HTTP/1.1 200 OK
            Content-Type: application/json
            Keep-Alive: timeout=60
            Set-Cookie: JSESSIONID=89EA9B9B9F00EAC2B8D2208649EA6260; Path=/; HttpOnly
            ```
- [x] 로그인 후 사용자의 정보를 조회하는 API를 구현한다.
    - 상단바 우측에 로그인 상태를 표현한다.
    - 인증 정보 조회 API 명세
        - Request
            ```http request
            GET /login/check HTTP/1.1
            Headers: Cookie=JSESSIONID=89EA9B9B9F00EAC2B8D2208649EA6260
            host: localhost:8080
            ```
        - Response
            ```
            HTTP/1.1 200 OK
            Connection: keep-alive
            Content-Type: application/json
            Date: Sun, 03 Mar 2024 19:16:56 GMT
            Keep-Alive: timeout=60
            Transfer-Encoding: chunked
            
            {
                "name": "어드민"
            }
            ```
- [ ] HandlerMethodArgumentResolver를 활용해 회원정보를 컨트롤러 메서드에 주입한다.

### 사용자 예약

- [x] `/reservation` 요청 시 사용자 예약 페이지를 응답한다.
    - 페이지는 `templates/reservation.html` 파일을 이용한다.
- [ ] 사용자는 날짜, 테마, 시간을 선택하고 결제를 하면 예약할 수 있다.
    - 사용자는 예약 가능한 시간을 확인하고, 원하는 시간에 예약을 할 수 있다.
    - 이미 예약된 날짜, 테마, 시간은 예약할 수 없으며, 예약 대기를 신청할 수 있다.
    - [ ] 지나간 날짜와 시간에 대한 예약 생성은 불가능하다.
    - [x] 예약 여부를 포함한 예약시간 목록 조회 API 명세
        - Request
            ```http request
            GET /times/available?date=2025-07-25&themeId=1 HTTP/1.1
            ```
        - Response
            ```
            HTTP/1.1 200
            Content-Type: application/json
            
            [
                {
                    "id": 1,
                    "startAt": "10:00"
                    "alreadyBooked": true
                },
                {
                    "id": 2,
                    "startAt": "12:00"
                    "alreadyBooked": false
                }
            ]
            ```
    - 사용자 예약 생성 API 명세
        - Request
            ```http request
            POST /reservations HTTP/1.1
            content-type: application/json
            Headers: Cookie=JSESSIONID=89EA9B9B9F00EAC2B8D2208649EA6260
            host: localhost:8080
          
            {
                "date": "2023-08-05",
                "themeId": 1,
                "timeId": 1,
                "paymentKey":"tgen_20240513184816ZSAZ9",
                "orderId":"MC4wNDYzMzA0OTc2MDgy",
                "amount":1000
            }
            ```
        - Response(어드민 예약 생성 API 응답과 동일)
            ```
            HTTP/1.1 201
            Content-Type: application/json
             
            {
                 "id": 1,
                 "member": {
                     "id": 1,
                     "name": "브라운",
                     "email": "brown@email.com"
                 }
                 "theme": {
                     "id": 1,
                     "name": "레벨1 탈출",
                     "description": "우테코 레벨1을 탈출하는 내용입니다.",
                     "thumbnail": "https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg"
                 }
                 "date": "2023-08-05",
                 "time": {
                     "id": 1,
                     "startAt": "10:00"
                 }
            }
            ```
- [ ] 사용자가 예약 생성 시, 로그인한 사용자 정보를 활용한다.
- [ ] 결제 기능은 외부의 결제 서비스를 사용하여 외부의 결제 API를 연동한다.
    - [ ] 결제 승인 API 호출에 실패 한 경우, 안전하게 에러를 핸들링 한다.
    - [ ] 사용자는 예약 실패 시, 결제 실패 사유를 알 수 있다.

### 예약 대기
- [ ] 이미 예약된 게임은 예약 대기를 신청할 수 있다.
- [ ] 신청한 예약 대기를 취소할 수 있다.

### 내 예약 목록 조회
- [x] `/reservation-mine` 요청 시 내 예약 페이지를 응답한다.
    - 페이지는 `templates/reservation-mine.html` 파일을 이용한다.
- [ ] 사용자의 예약과 예약 대기 목록을 조회한다.
    - [ ] 내 예약 페이지에서 예약 정보와 결제 정보를 확인할 수 있다.
    - [ ] 내 예약 목록의 예약 대기 상태에서 몇 번째 대기인지 확인할 수 있다.
- [ ] 내 예약 목록을 조회하는 API 명세
    - Request
        ```http request
        GET /reservations-mine HTTP/1.1
        Headers: Cookie=JSESSIONID=89EA9B9B9F00EAC2B8D2208649EA6260
        host: localhost:8080
        ```
    - Response
        ```
        HTTP/1.1 200
        Content-Type: application/json
        
        [
            {
                "reservationId": 1,
                "theme": "테마1",
                "date": "2024-03-01",
                "time": "10:00",
                "status": "예약",
                "paymentKey": "tgen_20240513184816ZSAZ9",
                "amount": 1000
            },
            {
                "reservationId": 2,
                "theme": "테마2",
                "date": "2024-03-01",
                "time": "12:00",
                "status": "예약",
                "paymentKey": "tgen_20240513184816ZSAZ9",
                "amount": 1000
            },
            {
                "reservationId": 3,
                "theme": "테마3",
                "date": "2024-03-01",
                "time": "14:00",
                "status": "2번째 예약대기",
                "paymentKey": "",
                "amount":
            }
        ]
        ```

## 홈페이지

### 인기 테마

- [x] `/` 요청 시 인기 테마 페이지를 응답한다.
    - 페이지는 `templates/index.html` 파일을 이용한다.
- [x] 최근 일주일을 기준으로 하여 해당 기간 내에 방문하는 예약이 많은 테마 10개를 조회한다.
    - 예를 들어 오늘이 4월 8일인 경우, 게임 날짜가 4월 1일부터 4월 7일까지인 예약 건수가 많은 순서대로 10개의 테마를 조회할 수 있어야 한다.
- 인기 테마 목록 조회 API 명세
    - Request
        ```http request
        GET /themes/popular?startDate=2025-06-17&endDate=2025-06-23&count=10 HTTP/1.1
        ```
    - Response
        ```
        HTTP/1.1 200
        Content-Type: application/json
        
        [
            {
                "id": 1,
                "name": "레벨1 탈출",
                "description": "우테코 레벨1을 탈출하는 내용입니다.",
                "thumbnail": "https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg"
            },
            {
                "id": 2,
                "name": "레벨2 탈출",
                "description": "우테코 레벨2를 탈출하는 내용입니다.",
                "thumbnail": "https://i.pinimg.com/236x/6e/bc/46/6ebc461a94a49f9ea3b8bbe2204145d4.jpg"
            }
        ]
        ```
## 예외처리
- [ ] 발생할 수 있는 예외 상황에 대한 처리를 하여, 사용자에게 적절한 응답을 한다.
    - [ ] 시간 생성 시 시작 시간에 유효하지 않은 값이 입력되었을 때
    - [ ] 예약 생성 시 예약자명, 날짜, 시간에 유효하지 않은 값이 입력 되었을 때
    - [ ] 특정 시간에 대한 예약이 존재하는데, 그 시간을 삭제하려 할 때
