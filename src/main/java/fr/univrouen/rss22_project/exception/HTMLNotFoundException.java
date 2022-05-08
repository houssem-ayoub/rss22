package fr.univrouen.rss22_project.exception;

import fr.univrouen.rss22_project.model.xml.ResponseXML;

public class HTMLNotFoundException extends Exception{
    private ResponseXML responseXML;

    public HTMLNotFoundException(ResponseXML responseXML) {
        super(responseXML.getDescription());
        this.responseXML = responseXML;
    }

    public ResponseXML getResponse() {
        return responseXML;
    }
}
