package com.olegdvd.temperature.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperature")
public class HomeController {

    //TODO should be removed or updated
    @GetMapping("/home")
    public String home() {
        return "home";
    }
}