package fr.univrouen.rss22_project.controller;

import fr.univrouen.rss22_project.exception.XMLNotFoundException;
import fr.univrouen.rss22_project.model.adapter.ItemAdapter;
import fr.univrouen.rss22_project.model.orm.ItemORM;
import fr.univrouen.rss22_project.model.service.FeedService;
import fr.univrouen.rss22_project.model.service.ItemService;
import fr.univrouen.rss22_project.model.xml.FeedXML;
import fr.univrouen.rss22_project.model.xml.ItemXML;
import fr.univrouen.rss22_project.model.xml.ItemXMLResumeList;
import fr.univrouen.rss22_project.model.validator.FeedXMLValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
public class RSSController {
    @Autowired private ItemService itemService;
    @Autowired private FeedService feedService;
    @Autowired private ItemAdapter itemAdapter;

    private final FeedXMLValidator feedXmlValidator = new FeedXMLValidator();
   @GetMapping(value = "/rss22/resume/xml",produces = MediaType.APPLICATION_XML_VALUE)
    public ItemXMLResumeList getItemResumeList(){
       return new ItemXMLResumeList(itemAdapter.adaptAllToResumeXML(itemService.list()));
    }
    @GetMapping(value = "/rss22/resume/xml/{guid}",produces = MediaType.APPLICATION_XML_VALUE)
    public ItemXML getItemDetails(@PathVariable("guid") String guid) throws XMLNotFoundException {
       try {
           return itemAdapter.adaptToXML(itemService.findItem(guid));
       }
       catch (EntityNotFoundException ignored){
           throw new XMLNotFoundException(guid);
       }
    }
    @PostMapping(value = "rss22/insert",produces = MediaType.APPLICATION_XML_VALUE)
    public String insertFeed(@RequestBody FeedXML data){
       if(feedXmlValidator.validate(data).isEmpty() && !feedService.exists(data)){
           feedService.save(data);
           return "<result>" +
                   "<status>INSERTED</status>" +
                   "<message>Le flux a été ajouté avec succès</message>"+
                   "</result>";
       }
       return "<result>" +
                "<status>ERROR</status>" +
                "<message>Une erreur et survenu</message>"+
                "</result>";
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
