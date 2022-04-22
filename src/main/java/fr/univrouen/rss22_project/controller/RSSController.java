package fr.univrouen.rss22_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RSSController {

    @GetMapping("/")
    public String index(){
        return "hello world";
    }
}
