package example.day043_260907_spring.Spring_practice4.controller;

import org.springframework.web.bind.annotation.RestController;

import example.day043_260907_spring.Spring_practice4.service.AcademyService;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
public class AcademyController {

    private final AcademyService academyService;

}
