package example.day043_260905_spring.Spring_practice4.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.day043_260905_spring.Spring_practice4.service.EnrollService;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/api/enroll")
public class EnrollController {

    private final EnrollService enrollService;
}
