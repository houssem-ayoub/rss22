package fr.univrouen.rss22_project.controller;

import fr.univrouen.rss22_project.model.feed.Feed;
import fr.univrouen.rss22_project.model.feed.Item;
import fr.univrouen.rss22_project.model.resume.ResumeItemList;
import fr.univrouen.rss22_project.model.service.FeedService;
import fr.univrouen.rss22_project.model.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
public class RSSController {
    @Autowired
    private ItemService itemService;
    @Autowired
    FeedService feedService;
   @GetMapping(value = "/rss22/resume/xml",produces = MediaType.APPLICATION_XML_VALUE)
    public ResumeItemList getResume(){

       return new ResumeItemList(itemService.list().stream().map(fr.univrouen.rss22_project.model.orm.Item::toResumeXMLObject).collect(Collectors.toList()));
    }
    @GetMapping(value = "/rss22/resume/xml/{guid}",produces = MediaType.APPLICATION_XML_VALUE)
    public Item getDetails(@PathVariable("guid") String guid){
        return itemService.findItem(guid).toXMLObject();
    }
    @PostMapping(value = "rss22/insert",produces = MediaType.APPLICATION_XML_VALUE)
    public String insertData(@RequestBody Feed data){
       feedService.save(data);
        return "<title>"+data.getTitle()+"</title>";
    }
    @DeleteMapping(value = "/rss22/delete/{guid}",produces = MediaType.APPLICATION_XML_VALUE)
    public String deleteData(@PathVariable("guid") String guid){
        return "<result>" +
                "<id>5555555</id>" +
                "<status>DELETED</status>" +
                "</result>";
    }
}
