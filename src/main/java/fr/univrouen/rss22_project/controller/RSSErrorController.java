package fr.univrouen.rss22_project.controller;

import fr.univrouen.rss22_project.exception.XMLErrorException;
import fr.univrouen.rss22_project.exception.XMLNotFoundException;
import fr.univrouen.rss22_project.model.xml.ResponseXML;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class RSSErrorController {
    @ExceptionHandler(XMLNotFoundException.class)
    public ResponseEntity<ResponseXML> notFoundXML(XMLNotFoundException e){
        System.out.println("Error: "+e.getResponse().getDescription());
        return new ResponseEntity<>(
                e.getResponse(),
                new MultiValueMapAdapter<>(
                        Map.of("Content-Type", List.of(MediaType.APPLICATION_XML_VALUE))
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(XMLErrorException.class)
    public ResponseEntity<ResponseXML> errorXML(XMLErrorException e){
        System.out.println("Error: "+e.getResponse().getDescription());
        return new ResponseEntity<>(
                e.getResponse()
                ,
                new MultiValueMapAdapter<>(
                        Map.of("Content-Type", List.of(MediaType.APPLICATION_XML_VALUE))
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
