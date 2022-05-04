package fr.univrouen.rss22_project.controller;

import fr.univrouen.rss22_project.model.adapter.ItemAdapter;
import fr.univrouen.rss22_project.model.orm.ItemORM;
import fr.univrouen.rss22_project.model.service.FeedService;
import fr.univrouen.rss22_project.model.service.ItemService;
import fr.univrouen.rss22_project.model.xml.FeedXML;
import fr.univrouen.rss22_project.model.xml.ItemXML;
import fr.univrouen.rss22_project.model.xml.resume.ItemXMLResumeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class RSSController {
    @Autowired private ItemService itemService;
    @Autowired private FeedService feedService;
    @Autowired private ItemAdapter itemAdapter;
   @GetMapping(value = "/rss22/resume/xml",produces = MediaType.APPLICATION_XML_VALUE)
    public ItemXMLResumeList getItemResumeList(){
       return new ItemXMLResumeList(itemAdapter.adaptAllToResumeXML(itemService.list()));
    }
    @GetMapping(value = "/rss22/resume/xml/{guid}",produces = MediaType.APPLICATION_XML_VALUE)
    public ItemXML getItemDetails(@PathVariable("guid") String guid){
        return itemAdapter.adaptToXML(itemService.findItem(guid));
    }
    @PostMapping(value = "rss22/insert",produces = MediaType.APPLICATION_XML_VALUE)
    public String insertFeed(@RequestBody FeedXML data){
       feedService.save(data);
        return "<title>"+data.getTitle()+"</title>";
    }
    @DeleteMapping(value = "/rss22/delete/{guid}",produces = MediaType.APPLICATION_XML_VALUE)
    public String deleteItem(@PathVariable("guid") String guid){
       try {
           ItemORM itemORM = itemService.findItem(guid);
           itemService.delete(itemORM);

           return "<result>" +
                   "<id>"+itemORM.getGuid()+"</id>" +
                   "<status>DELETED</status>" +
                   "<message>L'article a été supprimé avec succès</message>"+
                   "</result>";
       }
       catch (EntityNotFoundException e){
           return "<result>" +
                   "<id>"+guid+"</id>" +
                   "<status>ERROR</status>" +
                   "<message>L'article n'existe pas</message>"+
                   "</result>";
       }
    }
}
