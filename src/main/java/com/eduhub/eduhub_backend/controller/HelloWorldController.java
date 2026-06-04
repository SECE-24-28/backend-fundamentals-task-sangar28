package com.eduhub.eduhub_backend.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class HelloWorldController {
//    @GetMapping("Hello-World")
//    public String helloworld(){return "Hello World!";}
    private final Environment environment;

    @Value("${spring.application.name}")
    private String appName;
    public HelloWorldController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("Hello-World")
    public String helloWorld()
    {
        return "Hello World!";
    }

    @GetMapping("/env")
    public String getEnvirnomentVariable()
    {
        String port= environment.getProperty("local.server.port");
        return "App Name:"+appName+"\n"+"Port:"+port+"\n";
    }


}
