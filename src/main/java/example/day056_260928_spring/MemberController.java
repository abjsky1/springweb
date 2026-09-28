package example.day056_260928_spring;

import org.springframework.web.bind.annotation.CrossOrigin;
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

//  [2] 로그인 + 세션 ( 인증 성공시 성공한 회원정보 저장 / FK 용도로 사용 )
    @PostMapping ("/login")
    public MemberDto login( @RequestBody MemberDto memberDto , HttpSession session ){

    //  서비스에게 인증 확인
        MemberDto result = memberservice.login(memberDto);
        if (result == null) { return null; }

    //  인증 성공이면 세션에 인증한 회원정보 담아주기
    //  매개변수에 HTTPSession 객체 정의
    //  "login_member" 라는 key(이름) 로 로그인 성공한 memberDto value 정보 저장 
    //  Object 로 자동 업캐스팅
        session.setAttribute("login_member", result); // result : 아이디 회원번호 권한 정도 담은 dto 

        return result;

    }


//  [3] 내 정보 조회 + 세션 ( 이미 로그인된 회원이 내정보 요청 )
    @GetMapping ("/me")
    public MemberDto getMyInfo( HttpSession session ){
    //  사용자에게 추가로 입력받을 것 없음

    //  세션에서 특정한(login_member) 정보 꺼내기
        Object obj = session.getAttribute("login_member");

    //  세션정보 비어있으면 실패
        if(obj == null){return null;}

    //  존재하면 다운캐스팅 , Object => dto
        MemberDto memberDto = (MemberDto)obj;

    //  서비스에게 추가 정보 요청하여 반환
        return memberservice.getMyInfo(memberDto.getMno());

    }

//  [4] 로그아웃 + 세션 ( 초기화 )
    @PostMapping ("/logout")
    public boolean logout( HttpSession session ){
    //  사용자에게 추가로 입력받을 것 없음

    //  세션 안에 모든 정보 초기화
        session.invalidate();

    //  세션 안에 특정 정보 삭제
    //  session.removeAttribute("login_member");

        return true;

    }






}
