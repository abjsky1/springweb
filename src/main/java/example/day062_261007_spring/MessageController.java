package example.day062_261007_spring;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller 
@RequiredArgsConstructor 
public class MessageController {

//  1. 메세지 템플릿
    private final SimpMessageSendingOperations messageTemp;

//  2. 메세지 매핑 메소드
    @MessageMapping ("/chat/message")
    public void message( MessageDto messageDto ){

    //  ws://localhost:8080/ws-chat/pub/chat/message
    //  ws도메인/ws주소/ws-chat/pub/chat/message 요청시 실행되는 메소드
    //  내용물(body)들을 MessageDto 매핑
    //  [생략] 메세지 내용 영구 저장 --> DB(JPA)
    //  같은 방을 구독하는 클라이언트에게 메세지 전송

    //  messageTemp.convertAndSend("/보낼주소", 내용물);
    //  보낼주소 : ws://localhost:8080/sub/chat/room/2
    //  [생략] 만약 데이터베이스 안에 메세지 보낸 사람과 같은 방 조회 --> DB(JPA)
        messageTemp.convertAndSend("/sub/chat/room/"+messageDto.getRoomId() , messageDto);

    }


}
