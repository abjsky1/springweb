package example.day062_261007_spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration  // 스프링 컨테이너 (설정클래스) 빈 등록
// @Controller     // 스프링 컨테이너 (컨트롤러 클래스) 빈 등록
// @ResponseBody   // 스프링 컨테이너 (ResponseBody 클래스) 빈 등록
// @RestController // 스프링 컨테이너 (컨트롤러 + ResponseBody ( 응답객체 자동 직렬화 ) 클래스) 빈 등록
// @Service        // 스프링 컨테이너 (서비스 클래스) 빈 등록
// @Repository     // 스프링 컨테이너 (리포지토리 클래스) 빈 등록
// @Component      // 스프링 컨테이너 (스프링 아케텍처와 관계없는 클래스) 빈 등록
// 스프링에서 해당 클래스들을 확인하여 빈(객체) 생성하여 컨테이너(메모리/저장소) 저장
// 시점 : 컴파일 할 때, @SpringBootApplication 에서 @ComponentScan 가 하위 폴더들의 Component 
@EnableWebSocketMessageBroker  // STOMP 프로토콜 브로커 기능을 사용하는 컴포넌트 등록
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

//  implements : 인터페이스 구현 , extends : 클래스 상속 

//  인터페이스 주 역할 : 구현이 되지 않은 추상메소드를 가지고 있는 타입 (메소드/기능 통합)

//  @Override : 인터페이스 - 메소드 구현 , 상속 - 메소드 재정의 

    @Override
    public void configureMessageBroker( MessageBrokerRegistry registry ){

    //  구독( 양방향 연결 ) 요청하는 방법/주소 정의
    //  registry.enableSimpleBroker("/구독주소");
        registry.enableSimpleBroker("/sub");

    //  구독( 양방향 연결 )된 상태에서 메세지를 주고 받는 방법/주소/엔드포인트 등록
    //  registry.setApplicationDestinationPrefixes("/발행주소");
        registry.setApplicationDestinationPrefixes("/pub");

    }


//  
    @Override
    public void registerStompEndpoints( StompEndpointRegistry registry ) {
        
    //  registry.addEndpoint("/소켓주소").setAllowedOriginPatterns("*");
        registry.addEndpoint("/ws-chat").setAllowedOriginPatterns("*");

        
        
    }



}


/*
    HTTP : 단방향통신 , 무상태 , 클라이언트 요청 1개당 응답 1개 ( 요청 없이 응답 불가능 )
    
    WebSocket(프로토콜) : 양방향통신 , 상태유지 , 한 번 연결 후에 연결된 상태에서 양방향 통신 가능 

    사용법 :

        1. implementation 'org.springframework.boot:spring-boot-starter-websocket' 설치

        2. 브로커 설정 ( WebSocketConfig 브로커 설정 클래스 )

            - 구독 주소 : ws://localhost:8080/sub       ,   특정 방/경로 구독
    
            - 발행 주소 : ws://localhost:8080/pub       ,   특정 방/경로 메세지 전송

            - 소켓 주소 : ws://localhost:8080/ws-chat   ,   백엔드-프론트 연결

        3. 
*/