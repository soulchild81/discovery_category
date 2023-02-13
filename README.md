#discovery_category 프로젝트 

## - 카테고리 테스트 프로젝트 
    1. 카테고리의 등록 , 수정 , 삭제 , 조회 를 구현한 API 프로젝트 입니다.
    2. swagger 와 restdocs 를 사용하여 클라이언트와의 협업을 원할하게 할수 있게 함
    3. RESTFUL 한 API 구현 
    4. 단위 테스트 , 엔드투엔드 테스트를 통하여 restdocs 문서 빌드 추가 
       src/test/java/com/discovery/categoty/CategoryTest 파일에 기술
    5. 카테고리 depth 는 상위와 하위 두 단계만 있다고 가정후 진행 , 상위 카테고리 코드는 CU 하위는 CL 로 지정


## - UseLibrary
    Application --> SpringBoot 2.7.3
    DOCUMENT REFERENCE --> SpringRestDocs 2.0.6
    API TEST --> Swagger 2.9.2
    UNIT TEST --> Junit 5
    Lombok
    JPA
    H2
    JDK 11

## - 관련 URL 
  1. restdocs 주소 : http://localhost:8080/docs/api_doc.html
  2. swagger 주소 : http://localhost:8080/swagger-ui.html
  3. H2 웹콘솔 주소 : http://localhost:8080/h2-console    id:sa pw: password

## - 빌드 및 수행 
  1. git clone https://github.com/soulchild81/discovery_category.git
     프로젝트를 클론 받는다.
     
  2. cd discovery_category
     해당 경로로 이동 
     
  3. ./gradlew build 
     빌드 수행 
  
  4. cd /discovery_category/build/libs
     경로 이동

  5. java -jar discovery_category-0.0.1-SNAPSHOT.jar
     서버 구동
     <img width="1557" alt="스크린샷 2023-02-13 오후 2 34 22" src="https://user-images.githubusercontent.com/54564644/218378803-7bf1fa68-d493-496b-ac6b-f937c921fe89.png">

     
  6. repository 내에 있는 category_api.http 파일 에서 리퀘스트 수행하여 테스트 
    <img width="1275" alt="스크린샷 2023-02-13 오후 2 31 58" src="https://user-images.githubusercontent.com/54564644/218378636-8decaca3-aa0f-4456-9b33-dadde384ed58.png">
