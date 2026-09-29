package example.day057_260929_spring;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

// Spring MVC 패턴이 아닌 일반 객체 생성
@Component 
public class JwtUtil {

//  propertis 파일 안에 api 인증키 또는 개발자 보안 데이터들을 넣어 안전하게 사용하려는 목적
    @Value ("${jwt.secret}")
    private String key;

//  hmacSha 알고리즘 : 단방향 , 대칭키
    private final SecretKey secretKey = Keys.hmacShaKeyFor( key.getBytes( StandardCharsets.UTF_8 ) );

//  [1] JWT 토큰 생성 메소드
    public String createToken( Long mno ){

        String jwt = Jwts.builder()                  // 토큰 생성 시작
                            .subject( key )             // 토큰에 들어갈 내용(payload)들 (주로 식별번호 , 권한)
                            .issuedAt( new Date() )     // 토큰 생성 시간
                            .expiration( new Date( new Date().getTime() * 60 * 60 ) )   // 토큰 만료 시간
                            .signWith(secretKey)        // 비밀키로 전자서명
                            .compact();                 // 토큰 생성 끝 , 토큰 정보 문자열로 변환

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






}
