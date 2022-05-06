package fr.univrouen.rss22_project.controller;

import fr.univrouen.rss22_project.model.adapter.ItemAdapter;
import fr.univrouen.rss22_project.model.service.ItemService;
import fr.univrouen.rss22_project.model.transformer.XMLObjectTransformer;
import fr.univrouen.rss22_project.model.xml.ItemXML;
import fr.univrouen.rss22_project.model.xml.ItemXMLResumeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityNotFoundException;

@Controller
public class HTMLController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemAdapter itemAdapter;
    @Value("${app.version}")
    private String version = null;
    private final XMLObjectTransformer itemXMLTransformer = new XMLObjectTransformer();
    @GetMapping(value = "/")
    public String getIndex(Model model){
        model.addAttribute("version",version);
        return "index";
    }
    @GetMapping(value = "/help")
    public String getHelp(){
        return "help";
    }

    @GetMapping(value = "/rss22/resume/html",produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String getResume(){
        return itemXMLTransformer.toHTMLString(ItemXMLResumeList.class,"item-resume-list",new ItemXMLResumeList(itemAdapter.adaptAllToResumeXML(itemService.list())));
    }
    @GetMapping(value = "/rss22/html/{guid}",produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String getDetails(@PathVariable("guid") String guid){
        try {
            return itemXMLTransformer.toHTMLString(ItemXML.class,"item",itemAdapter.adaptToXML(itemService.findItem(guid)));
        }
        catch (EntityNotFoundException ignored){
            return "<p>Artile avec le guid: "+guid+"n'existe pas</p>";
        }

    }
}
