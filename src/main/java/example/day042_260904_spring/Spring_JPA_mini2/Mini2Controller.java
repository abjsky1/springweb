package example.day042_260904_spring.Spring_JPA_mini2;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class Mini2Controller {

    private final Mini2Service mini2Service;
}
