package ru.alexsolution.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import jdk.jfr.ContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.alexsolution.services.AwsService;

@RestController
@RequiredArgsConstructor
@Hidden
public class TestController {

    private final AwsService awsService;

    @PostMapping("/test")
    void test(@RequestBody MultipartFile file){
        awsService.uploadImage(file);
    }

}
