package com.example.prj1be20231109.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError() {
        return "error"; // 수정된 부분: "/" -> "error"
    }
}



//package com.example.prj1be20231109.controller;
//
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class AppErrorController implements ErrorController {
//
//    @GetMapping("/error")
//    public String handleError() {
//        return "error"; // 수정된 부분: "/" -> "error"
//    }
//}