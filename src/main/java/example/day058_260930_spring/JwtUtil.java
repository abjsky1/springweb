package example.day058_260930_spring;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

// Spring MVC 패턴이 아닌 일반 객체 생성
@Component 
public class JwtUtil {

//  propertis 파일 안에 api 인증키 또는 개발자 보안 데이터들을 넣어 안전하게 사용하려는 목적
    @Value ("${jwt.secret}")
    private String key;

//  hmacSha 알고리즘 : 단방향 , 대칭키
    private SecretKey secretKey;

    @PostConstruct // 객체 생성시 의존성 완료된 후에 아래 메소드가 1번 호출 되도록 하는 어노테이션
    public void init(){
        this.secretKey = Keys.hmacShaKeyFor( key.getBytes( StandardCharsets.UTF_8 ) );
    }


//  [1] JWT ACCESS 토큰 생성 메소드
    public String createAccessToken( Long mno ){

        String jwt = Jwts.builder()                                 // 토큰 생성 시작
                            .claim("type", "ACCESS")    // 
                            .subject( mno+"" )                      // 토큰에 들어갈 내용(payload)들 (주로 식별번호 , 권한)
                            .issuedAt( new Date() )                 // 토큰 생성 시간
                            .expiration( new Date( new Date().getTime() * 1000L * 60 * 30 ) )   // 토큰 만료 시간 : new Date().getTime() * 1000L * 60 * 30  ==>  30분
                            .signWith(secretKey)                    // 비밀키로 전자서명
                            .compact();                             // 토큰 생성 끝 , 토큰 정보 문자열로 변환

        System.out.println( jwt );

        return jwt;
    }


//  [2] JWT 토큰 검증 메소드
    public Long getMnoFromToken( String token ){

        try{
            Claims claims = Jwts.parser()                   // 파싱(가져오기)
                                .verifyWith( secretKey )    // 전자서명 이용한 검증
                                .build()
                                .parseSignedClaims(token)   // 파싱할 토큰
                                .getPayload();              // JWT 안에 payload 값 반환

        //  payload 안에 subject 꺼내기 (문자열타입)
            Long mno = Long.parseLong( claims.getSubject() ); 

            System.out.println(mno);

            return mno;
        }
        catch(Exception e){ System.out.println(e); return null; }

    }


//  [3] JWT REFRESH 토큰 생성 메소드
    public String createRefreshToken( Long mno ){

        String jwt = Jwts.builder()                                 // 토큰 생성 시작
                            .claim("type", "REFRESH")    // 
                            .subject( mno+"" )                      // 토큰에 들어갈 내용(payload)들 (주로 식별번호 , 권한)
                            .issuedAt( new Date() )                 // 토큰 생성 시간
                            .expiration( new Date( new Date().getTime() * 1000L * 60 * 60 * 24 * 7 ) )   // 토큰 만료 시간 , ACCESS 만료시간보다 길게 설정(ex: new Date().getTime() * 1000L * 60 * 60 * 24 * 7  ==>  7일 )
                            .signWith(secretKey)                    // 비밀키로 전자서명
                            .compact();                             // 토큰 생성 끝 , 토큰 정보 문자열로 변환

        System.out.println( jwt );

        return jwt;
    }



}
