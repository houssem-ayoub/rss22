package fr.univrouen.rss22_project.controller;

import fr.univrouen.rss22_project.model.resume.ItemResume;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HTMLController {
    @GetMapping(value = "/help")
    public String getHelp(){
        return "help";
    }
    @GetMapping(value = "/rss22/resume/html",produces = MediaType.TEXT_HTML_VALUE)
    public String getResume(Model model){
        model.addAttribute("items", List.of(
                new ItemResume("5555555","Article 1","24/02/2012"),
                new ItemResume("2222222","Article 2","24/05/2020")
        ));
        return "items-resume";
    }
    @GetMapping(value = "/rss22/html/{guid}",produces = MediaType.TEXT_HTML_VALUE)
    public String getDetails(@PathVariable("guid") String guid,Model model){
        model.addAttribute("guid",guid);
        return "item-details";
    }
}
