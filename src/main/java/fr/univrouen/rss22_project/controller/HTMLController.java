package fr.univrouen.rss22_project.controller;

import fr.univrouen.rss22_project.model.adapter.ItemAdapter;
import fr.univrouen.rss22_project.model.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HTMLController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemAdapter itemAdapter;
    @GetMapping(value = "/help")
    public String getHelp(){
        return "help";
    }
    @GetMapping(value = "/rss22/resume/html",produces = MediaType.TEXT_HTML_VALUE)
    public String getResume(Model model){
        model.addAttribute("items",
                itemAdapter.adaptAllToResumeXML(itemService.list()));
        return "items-resume";
    }
    @GetMapping(value = "/rss22/html/{guid}",produces = MediaType.TEXT_HTML_VALUE)
    public String getDetails(@PathVariable("guid") String guid,Model model){
        model.addAttribute("item",itemAdapter.adaptToXML(itemService.findItem(guid)));
        return "item-details";
    }
}
