package example.day059_261001_spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;


@RestController @RequestMapping ("/api/session")
public class SessionController {
    

//  세션 (개인저장소) Object 타입이기 때문에 타입변환 중요

//  [1] 쿼리스트링으로 전달받은 data 값을 세션(HttpSession)에 누적 저장
    @GetMapping ("/add")
    public List<String> add( @RequestParam (name = "data") String data , HttpSession session ){
        List<String> list = (List<String>) session.getAttribute("listKey");
        if( list == null ) list = new ArrayList<>();
        list.add(data);
        session.setAttribute("listKey" , list );
        return list;
    }

    

//  [2] 현재 세션(HttpSession)에 저장된 전체 data 목록 조회
    @GetMapping ("/all")
    public List<String> all( HttpSession session ){
        List<String> list = (List<String>) session.getAttribute("listKey");
        if( list == null ) list = new ArrayList<>();
        return list;
    }

}
