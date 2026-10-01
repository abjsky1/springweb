package example.day059_261001_spring;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;


@RestController @RequestMapping ("/api/cookie")
public class CookieController {

    private final ObjectMapper objectMapper = new ObjectMapper(); // 직렬화 객체

//  [3] 쿼리스트링으로 전달받은 data 값을 브라우저 쿠키(Cookie)에 누적 저장
    @GetMapping ("/add")
    public String add( @RequestParam (name = "data") String data , HttpServletResponse response , @CookieValue (value = "listKey" , required = false) String cookie ) throws JsonProcessingException{
        
        List<String> list;

        if(cookie == null){ list = new ArrayList<>(); }
        else{ list = objectMapper.readValue(cookie, List.class); }

        list.add(data);

        String json = objectMapper.writeValueAsString(list);

        ResponseCookie cookie1 = ResponseCookie.from("listKey", URLEncoder.encode(json, StandardCharsets.UTF_8) )
                .path("/")
                .build();
        
        response.addHeader(HttpHeaders.SET_COOKIE, cookie1.toString());
        return "쿠키저장성공";
        
    }


//  [4] 브라우저 쿠키(Cookie)에 누적 저장된 전체 data 목록 조회
    @GetMapping("all")
    public List<String> all(@CookieValue(name = "listKey", required = false) String cookieData) throws JsonMappingException, JsonProcessingException {
        if (cookieData == null) {
            return Collections.emptyList();
        }
        return objectMapper.readValue(cookieData, List.class );
    }




}
