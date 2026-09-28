package example.day052_260918_spring.teampj_api;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@CrossOrigin (origins = "http://localhost:5173")
public class ApiController {

    private final ApiService apiService;

    @GetMapping (value = "/t3if", produces = "application/json; charset=UTF-8")
    public Map<String,Object> findAll(){
        return apiService.findAll();
    }

    @GetMapping(value = "/test1", produces = "application/xml")
    public Map<String, Object> test1() {
        return apiService.test1();
    }

    @GetMapping(value = "/koo")
    public Map<String,Object> Koo() {
        return apiService.Koo();
    }

    @GetMapping("/practice")
    public Map<String, Object> practice() {
        return apiService.practice();
    }
    
}
