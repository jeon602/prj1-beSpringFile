package com.example.prj1be20231109.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppErrorController implements ErrorController {
    @GetMapping("/error")
    public String haddfleError(){
        return "/";
    }
}
