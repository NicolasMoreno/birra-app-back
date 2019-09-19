package com.birraapp.birraappbackend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HelloWorldController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String healthTesting() {
        return "Hello world!";
    }
}
