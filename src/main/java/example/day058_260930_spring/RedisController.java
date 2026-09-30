package example.day058_260930_spring;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController @RequestMapping ("/api/redis") @RequiredArgsConstructor 
public class RedisController {

//  [1] 레디스 조작 객체( 문자열 기반의 자료 레디스에 삽입/조회/수정/삭제 )
    private final StringRedisTemplate stringRedisTemplate;

//  레디스를 키고 실행. redis-server.exe

    //  1.
    @GetMapping ("/test1")
    public Map<String,Object> test1( ){

    //  2. 레디스에 자료 삽입 , .opsForValue().set( key : value ) , 문자열타입
    //  key 는 중복 불가
        stringRedisTemplate.opsForValue().set("김길리", "90");
        stringRedisTemplate.opsForValue().set("홍길동", "80");
        stringRedisTemplate.opsForValue().set("가나디", "70");

    //  3. 레디스에 자료 조회 , .keys("*") , 모든 자료들의 키 조회 , Set<String> 컬렉션으로 반환
    //  참고 : 컬렉션프레임워크 ( List , Map , Set )
        Set<String> keys = stringRedisTemplate.keys("*");

        Map<String,Object> map = new HashMap<>();

        for(String key : keys){

            String data = stringRedisTemplate.opsForValue().get(key);

            map.put( key , data );
        
        }
        return map;


    }


//  *****************************   //

    private final ObjectMapper objectMapper = new ObjectMapper();  // 직렬화

    @PostMapping ("/member")
    public boolean save( @RequestBody MemberDto memberDto) throws JsonProcessingException{
    
    //  1. 중복 없는 key 구성 ( ex: 도메인명 : 식별키 )
        String key = "member:"+memberDto.getMno();  // ex)  member:3

    //  2. 문자열템플릿에 DTO/자바객체 대입 , DTO -> 문자열( 직렬화 )
    //  .writeValueAsString( 자바객체 ) , 일반예외 발생 (throws 던져)
        String str = objectMapper.writeValueAsString( memberDto );

    //  3. 레디스에 저장
        stringRedisTemplate.opsForValue().set(key, str);
        
        return true;

    
    }



}
