package com.olegdvd.temperature.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String helloWorld(){
        return "main";
    }
}
