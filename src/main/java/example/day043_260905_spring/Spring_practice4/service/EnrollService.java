package example.day043_260905_spring.Spring_practice4.service;

import org.springframework.stereotype.Service;

import example.day043_260905_spring.Spring_practice4.model.repository.EnrollRepository;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class EnrollService {

    private final EnrollRepository enrollRepository;

    
}
