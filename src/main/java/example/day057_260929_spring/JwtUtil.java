package example.day057_260929_spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// Spring MVC 패턴이 아닌 일반 객체 생성
@Component 
public class JwtUtil {

    @Value ("${jwt.secret}")
    private String key;
}
