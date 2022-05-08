package fr.univrouen.rss22_project.controller;

import fr.univrouen.rss22_project.exception.XMLErrorException;
import fr.univrouen.rss22_project.exception.XMLNotFoundException;
import fr.univrouen.rss22_project.model.adapter.ItemAdapter;
import fr.univrouen.rss22_project.model.orm.ItemORM;
import fr.univrouen.rss22_project.model.service.FeedService;
import fr.univrouen.rss22_project.model.service.ItemService;
import fr.univrouen.rss22_project.model.validator.FeedXMLValidator;
import fr.univrouen.rss22_project.model.xml.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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
    @GetMapping(value = "/rss22/search",produces = MediaType.APPLICATION_XML_VALUE)
    public ItemXMLResumeList getSearchedItemList(@RequestParam(name = "date",defaultValue = "") String year,@RequestParam(name = "titreListe",defaultValue = "") String titleListParam) throws XMLErrorException{
      System.out.println("Year:"+year);
      System.out.println("titleListParam:"+titleListParam);
       if (year.isBlank()&&titleListParam.isBlank()){
           return new ItemXMLResumeList(itemAdapter.adaptAllToResumeXML(itemService.list()));
       }
       if (year.length()>4 || (!year.matches("\\d{4}") && !year.isBlank())){
           throw new XMLErrorException(new ResponseXML(null,"Error","la date doit être sous la forme [yyyy]"));
       }
       if(titleListParam.isBlank()){
           return new ItemXMLResumeList(itemAdapter.adaptAllToResumeXML(itemService.findAllByYear(year)));
       }
       if (year.isBlank()){
           return new ItemXMLResumeList(itemAdapter.adaptAllToResumeXML(itemService.findAllByWordList(titleListParam)));
       }
        return new ItemXMLResumeList(itemAdapter.adaptAllToResumeXML(itemService.findAllByWordListAndYear(titleListParam,year)));
    }
    @GetMapping(value = "/rss22/xml/{guid}",produces = MediaType.APPLICATION_XML_VALUE)
    public ItemXML getItemDetails(@PathVariable("guid") String guid) throws XMLNotFoundException {
       try {
           return itemAdapter.adaptToXML(itemService.findItem(guid));
       }
       catch (EntityNotFoundException ignored){
           throw new XMLNotFoundException(new ResponseXML(guid,"ERROR","l'article demandé n'existe pas"));
       }
    }
    @PostMapping(value = "rss22/insert",produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseXML insertFeed(@RequestBody FeedXML data) throws XMLErrorException {
        Optional<String> errorValidationOptional= feedXmlValidator.validate(data);
        if (errorValidationOptional.isPresent())
            throw new XMLErrorException(new ResponseXML(null,"Error",errorValidationOptional.get()));
        if (feedService.exists(data))
            throw new XMLErrorException(new ResponseXML(null,"Error","Le flux existe déjà"));
           String guid = feedService.save(data);
           return  new ResponseXML(guid,"INSERTED","Le flux a été ajouté avec succès");
       }
    @DeleteMapping(value = "/rss22/delete/{guid}",produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseXML deleteItem(@PathVariable("guid") String guid) throws XMLNotFoundException{
       try {
           ItemORM itemORM = itemService.findItem(guid);
           itemService.delete(itemORM);
           return new ResponseXML(guid,"DELETED","L'article a été supprimé avec succès");
       }
       catch (EntityNotFoundException e){
           throw new XMLNotFoundException(new ResponseXML(guid,"ERROR","l'article demandé n'existe pas"));
       }
    }
}
