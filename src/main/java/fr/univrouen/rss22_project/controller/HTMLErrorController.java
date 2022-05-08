package fr.univrouen.rss22_project.controller;

import fr.univrouen.rss22_project.exception.HTMLNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HTMLErrorController {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HTMLNotFoundException.class)
    public String notFound(Model model, HTMLNotFoundException e){
        model.addAttribute("guid",e.getResponse().getGuid());
        return "error";
    }
}
