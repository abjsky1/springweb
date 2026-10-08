package example.day062_261007_spring;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/*
    WebSocket + STOMP : ( 양방향 , 클라이언트 <--> 서버 )  ex. 채팅구현

    SSE : ( 단방향 , 클라이언트 <-- 서버 )  ex. 알림
*/

@Service 
public class NoticeService {

//  1. 연결된 클라이언트들의 정보 보관하는 리스트
//  * 서버가 클라이언트에게 비동기 요청을 보내기위한 HTTP 응답 (스트리밍) 객체 , 연결 유지
//  * 동기/비동기 :
//      - 동기화 : 하나의 메소드를 순차적으로 실행
//      - 비동기 : 하나의 메소드를 동시에 실행
//  * ArrayList() : 동기화 지원 안 함.
//  * Vector()    : 동기화 지원 함
//  * 동기화 필요 목적 : 하나의 서버가 구독과 메세지 전송 등 동시 다발적으로 들어오는 것들에 대해 순차적 처리
//  * CopyOnWriteArrayList() : 여러개의 요청들을 동시에 접속, 종료, 메세지전송 등을 했을 때, 동기화 제공
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

//  2. 클라이언트 구독 처리 ( SseEmitter 생성하여 리스트에 저장 )
    public SseEmitter subscribe( ){

    //  SseEmitter 객체 생성
        SseEmitter emitter = new SseEmitter();

    //  리스트에 저장
        emitters.add( emitter );

    //  안전하게 클라이언트 연결이 비정상이면 리스트에서 삭제
    //  * 리액트에서 중복되는 구독 신청으로 문제 발생
        emitter.onCompletion( () -> emitters.remove(emitter) );
        emitter.onTimeout( () -> emitters.remove(emitter) );
        emitter.onError( (e) -> emitters.remove(emitter) );

    //  생성된 객체 반환
        return emitter;
        
    }

//  3. 메세지 전송 ( 서버가 클라이언트에게 메세지 전송 )
    public void onMessage( String message ){
        
    //  현재 리스트에 저장/접속/구독된 emitter 들에게 메세지 보내기
        for( SseEmitter emitter : emitters ){

            
        //  .send( SseEmitter.event().name("구독식별").data(내용물) );
            try{
                emitter.send( SseEmitter.event().name("notice").data(message) );
            }catch( IOException e ){ emitters.remove(emitter); }
        } 

    }



}
