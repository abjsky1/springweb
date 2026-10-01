package example.day059_261001_spring;

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



    

}
