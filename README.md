#discovery_category 프로젝트 

## - 카테고리 테스트 프로젝트 
    1. 카테고리의 등록 , 수정 , 삭제 , 조회 를 구현한 API 프로젝트 입니다.
    2. swagger 와 restdocs 를 사용하여 클라이언트와의 협업을 원할하게 할수 있게 함


## - UseLibrary
    Application --> SpringBoot 2.7.3
    DOCUMENT REFERENCE --> SpringRestDocs 2.0.6
    API TEST --> Swagger 2.9.2
    UNIT TEST --> Junit 5
    Lombok
    JPA
    H2

## - 관련 URL 
  ### restdocs 주소 : http://localhost:8080/docs/api_doc.html
  ### swagger 주소 : http://localhost:8080/swagger-ui.html

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
