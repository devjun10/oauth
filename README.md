## Oauth 인증

코드스쿼드 마스터스 코스에서 Sidedish 프로젝트를 수행하며 Oauth 추가 학습을 위해 만든 사이드 프로젝트입니다.

<br/><br/><br/>

### @ConfigurationProperties

이를 사용하기 위해서는 @EnableConfigurationProperties 어노테이션을 통해 설정 파일의 데이터르 바인딩할 클래스를 넣어준다.

<br/><br/>

### ClientRegistration

스프링 시큐리티는 애플리케이션 로딩 시점에 Oauth2ClientProperties를 빈으로 등록하고 내부 값들을 각 OAuth2 서버 별로 ClientRegistration 객체를 만들어 저장한다.

<br/><br/>

## ClientRegistrationRepository

OAuth2 App Server에서 요청하기 위한 필드의 정보들을 저장하는 저장소. 
