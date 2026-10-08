package example.day062_261007_spring;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/api/sse")
@CrossOrigin ( origins = "*")
public class NoticeController {

//  0. 알림 서비스 호출
    private final NoticeService noticeService;

//  1. 알림 구독 매핑
//  * 응답 content-type JSON 이 아닌 스트림 타입으로 변경
//  * MediaType 자동완성시 import org.springframework.http.MediaType;
    @GetMapping (value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe( ){
        return noticeService.subscribe();
    }


//  2. 알림 메세지 전송 *테스트용* 보통 글 작성 서비스에서 호출
    @GetMapping ("/message")
    public void onMessage(@RequestParam (name = "msg") String msg ){
        noticeService.onMessage(msg);
    }


}
