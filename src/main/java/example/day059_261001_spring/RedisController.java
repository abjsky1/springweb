package example.day059_261001_spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController @RequestMapping ("/api/redis") @RequiredArgsConstructor 
public class RedisController {

    private final StringRedisTemplate redisTemplate;

    Integer a = 1;

    @GetMapping("add")
    public String addRedisData(@RequestParam("data") String data) {
        redisTemplate.opsForValue().set("data"+ a+"", data); 
        a++;
        return "레디스저장성공";
    }

    @GetMapping("all")
    public List<String> getAllRedisData() {
        Set<String> keys = redisTemplate.keys("*");
        List<String> list = new ArrayList<>();
        for( String key : keys ){ 
            String data = redisTemplate.opsForValue().get(key);
            list.add(  data );
        }
        return list;
    }

}
