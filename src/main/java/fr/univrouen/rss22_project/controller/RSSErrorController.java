package fr.univrouen.rss22_project.controller;

import fr.univrouen.rss22_project.exception.XMLNotFoundException;
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
    public ResponseEntity<String> notFountXML(XMLNotFoundException e){
        return new ResponseEntity<>(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                        "<rss:response xmlns:rss=\"http://univrouen.fr/rss22\">\n" +
                        "<rss:guid>"+e.getMessage()+
                        "</rss:guid>\n<rss:status>ERROR</rss:status>\n</rss:response>",
                new MultiValueMapAdapter<>(
                        Map.of("Content-Type", List.of(MediaType.APPLICATION_XML_VALUE))
                ),
                HttpStatus.NOT_FOUND
        );
    }
}
