package example.day065_261013_spring;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/api")
public class CrawlingController {

    private final CrawlingService crawlingService;

    @GetMapping ("/craw1")
    public List<String> craw1(){
        return crawlingService.craw1();
    }

    @GetMapping ("/craw2")
    public List<Map<String,Object>> craw2(){
        return crawlingService.craw2();
    }









}
