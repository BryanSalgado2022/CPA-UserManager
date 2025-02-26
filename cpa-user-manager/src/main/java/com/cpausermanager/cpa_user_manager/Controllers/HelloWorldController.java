package com.cpausermanager.cpa_user_manager.Controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HelloWorldController {
    @RequestMapping("hello")
    public String HelloWorld(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello " + name; 
    }
    
}
