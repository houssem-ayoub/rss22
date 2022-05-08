package fr.univrouen.rss22_project.exception;

import fr.univrouen.rss22_project.model.xml.ResponseXML;

public class XMLErrorException extends Exception{
    private ResponseXML responseXML;

    public XMLErrorException(ResponseXML responseXML) {
        super(responseXML.getDescription());
        this.responseXML = responseXML;
    }

    public ResponseXML getResponse() {
        return responseXML;
    }
}
