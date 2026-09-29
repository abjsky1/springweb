package example.day057_260929_spring;

import java.time.Duration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("/api/member")
@RequiredArgsConstructor 
@CrossOrigin ( origins = "http://localhost:5173" , allowCredentials = "true")
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



//  [1] 회원가입
    @PostMapping ("signup")
    public boolean signup( @RequestBody MemberDto memberDto ){

        return memberservice.signup( memberDto );
    }

    private final JwtUtil jwtUtil;

//  [2] 로그인 + 세션 ( 인증 성공시 성공한 회원정보 저장 / FK 용도로 사용 )
    @PostMapping ("/login")
    public MemberDto login( @RequestBody MemberDto memberDto , HttpServletResponse response ){

    //  서비스에게 인증 확인
        MemberDto result = memberservice.login(memberDto);
        if (result == null) { return null; }

    //  로그인 성공시 쿠키 생성/발급
    //  쿠키는 세션과 다르게 클라이언트에 저장되므로 회원번호만 저장 (민감한 정보는 쿠키에 넣지 말 것)

    //  ResponseCookie cookie = ResponseCookie.from("쿠키명" , "쿠키값").build();
    //  result.getMno()+""  :  정수 -> 문자 타입변환  ==>>  정수+""  ,  String.valueOf(정수) 
    //  .path("/") 쿠키를 사용할 경로
    //  .maxAge( Duration.ofXXX(1) ) : 쿠키의 유효기간 , 1일
    //  .httpOnly(true) : JS 이용한 탈취 방지 , XSS 공격 
    //  .secure(false) : HTTPS 에서만 사용 , 개발단계 : false , 배포단계 : true
    //  .sameSite("Lax") : CSRF 공격 방어

    //  쿠키값을 jwt 안전하게 변경
    //  result.getMno()+""  ==>> 
        String jwt = jwtUtil.createToken( result.getMno()) ; 

        ResponseCookie cookie = ResponseCookie.from("login_member", jwt)
                                              .path("/")
                                              .maxAge(Duration.ofDays(1))
                                              .httpOnly(true)
                                              .secure(false)
                                              .sameSite("Lax")
                                              .build();
    
    //  응답 헤더에 쿠키 등록 , response.setHeader
        response.setHeader( HttpHeaders.SET_COOKIE , cookie.toString() );
        
        return result;

    }


//  [3] 내 정보 조회 + 쿠키 ( 이미 로그인된 회원이 내정보 요청 )
    @GetMapping ("/me")
    public MemberDto getMyInfo( @CookieValue ( value = "login_member" , required = false ) String token ){
    //  요청한 브라우저의 쿠키 가져오기. 

    //  만약 loginMno 가 없다면 비로그인
        if(token == null){return null;}

    //  쿠키에 저장된 token 이용하여 회원번호 찾기
        Long loginMno = jwtUtil.getMnoFromToken(token);


    //  로그인 중이면 서비스에게 회원정보 요청
    //  참고 : 문자 -> 정수  변환 방법 : 래퍼클래스명.parse타입 (문자)
        return memberservice.getMyInfo( loginMno );

    }


//  [4] 로그아웃 + 쿠키 ( 초기화 )
    @PostMapping ("/logout")
    public boolean logout( HttpServletResponse response ){

    //  삭제할 쿠키명과 동일한 이름으로 maxAge(0) 하여 재발급
        ResponseCookie cookie = ResponseCookie.from("login_member", "")
                                              .path("/")
                                              .maxAge(0)
                                              .httpOnly(false)
                                              .secure(false)
                                              .build();

    //  응답 헤더에 쿠키 등록 , response.setHeader
        response.setHeader( HttpHeaders.SET_COOKIE , cookie.toString() );
        
        return true;

    }






}
