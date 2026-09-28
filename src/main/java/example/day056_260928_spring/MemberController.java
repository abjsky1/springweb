package example.day056_260928_spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("/api/member")
@RequiredArgsConstructor 
public class MemberController {

    private final MemberService memberservice;

    @GetMapping ("")
    public String test( HttpServletRequest request ){

    //  HttpServletRequest : HTTP 요청이 들어오면 요청 정보를 담겨 있는 서블릿 객체

    //  요청한 클라이언트의 IP (로그/위치추적/조회수 등)
        System.out.println( request.getRemoteAddr() ); 
        
    //  요청한 클라이언트의 브라우저 정보
        System.out.println( request.getHeader("User-Agent") );
    
    //  요청한 클라이언트의 세션 객체 정보 (로그인성공정보/인증번호/비회원제장바구니 등)
    //  세션 객체 : 톰캣 서버 안에 브라우저마다 독립적인 저장소
        System.out.println( request.getSession() );
    
    //  세션 객체 안에 여러개 정보 저장 가능
        HttpSession session = request.getSession();

    //  세션 식별 번호
        System.out.println( session.getId() );

    //  세션 생성 시간
        System.out.println( session.getCreationTime() );

    //  세션 마지막 접근 시간
        System.out.println( session.getLastAccessedTime() );

    //  세션 생명 주기 (자동로그아웃) : 기본값 30분
        System.out.println( session.getMaxInactiveInterval() );

    //  세션 정보 저장 = 로그인 , 호출 = 마이페이지 , 삭제 = 로그아웃
    //  map(key:value) 구조로 구성  ,  주의 : value 의 타입은 object 라서 타입변환 필요
        session.setAttribute("data", "사과");

    //  key 를 이용한 value 호출
        System.out.println( session.getAttribute("data") );

    //  세션 초기화
        session.invalidate();

        return session.getId();

    }




    @PostMapping ("")
    public boolean signup( @RequestBody MemberDto memberDto ){

        return memberservice.signup( memberDto );
    }


}
