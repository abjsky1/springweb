package example.day064_261012_spring;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/api")
@CrossOrigin (origins = "http://localhost:5173")
public class LLMController {

    private final LLMService llmService;


//  1. http://localhost:8080/api/chat1?prompt=안녕
    @GetMapping ("/chat1")
    public String chat1( @RequestParam (name = "prompt") String prompt ){
        
        return llmService.chat1( prompt );
    }

//  2. http://localhost:8080/api/chat2
    @GetMapping ("/chat2")
    public String chat2( ){
        
        return llmService.chat2( );
    }

//  3. http://localhost:8080/api/chat3
    @GetMapping ("/chat3")
    public String chat3( @RequestParam (name = "topic") String topic ){
        
        return llmService.chat3( topic );
    }

//  4. http://localhost:8080/api/chat4?userMessage=질문
    @GetMapping ("/chat4")
//  [기존코드]
//  public String chat4( @RequestParam (name = "message") String message ){

//  [문제] 프론트(ChatBot.jsx) 에서 chat4 요청시 400 (Bad Request) 에러 발생 → 서비스(LLM) 까지 가지도 못함
//  [원인] 프론트는 쿼리스트링 이름을 userMessage 로 보냄 → /api/chat4?userMessage=내용
//          백엔드는 @RequestParam (name = "message") 로 message 라는 이름을 찾음
//          @RequestParam 은 기본값이 필수(required = true) → message 가 없으니 스프링이 메소드 실행 전에 400 응답
//          * 400 : 서버는 연결됐지만 '요청 형식이 잘못됨' ( 404 는 주소가 없음 , 500 은 서버 코드 실행 중 에러 )
//          * chat1( prompt ) , chat3( topic ) 은 프론트와 이름이 같아서 정상 동작
//  [해결] 프론트가 보내는 이름과 같게 name = "userMessage" 로 변경 ( 매개변수 변수명 message 는 그대로 사용 가능 )
    public String chat4( @RequestParam (name = "userMessage") String message ){
        
        return llmService.chat4( message );
    }




}
