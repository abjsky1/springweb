package example.day043_260907_spring.Spring_practice4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.day043_260907_spring.Spring_practice4.model.dto.EnrollDto;
import example.day043_260907_spring.Spring_practice4.service.EnrollService;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/api/enroll")
public class EnrollController {

    private final EnrollService enrollService;


//  [등록]
    @PostMapping 
    public boolean save(@RequestBody  EnrollDto enrollDto){

        return enrollService.save(enrollDto);
    }


//  [개별조회]
    @GetMapping EnrollDto findDetail(@RequestParam(name = "enrollId") Integer enrollId){
        
        return enrollService.findDetail(enrollId);
    }




}
