package com.jpastudy.mybook.domain.sample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class SampleController {
    @GetMapping("/hello")
    public String Hello(){
        return "{ \"message\": \"hello\" }";
    }
}
