package fr.univrouen.rss22_project.model.validator;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;
import java.util.ArrayList;
import java.util.List;

public class FeedXMLValidationEventHandler implements ValidationEventHandler {
    List<String> errors = new ArrayList<>();
    @Override
    public boolean handleEvent(ValidationEvent validationEvent) {
        if (validationEvent.getSeverity() == validationEvent.ERROR ||
                validationEvent.getSeverity() == validationEvent.FATAL_ERROR)
        {
            errors.add(validationEvent.getMessage());
        }
        return true;
    }
    public List<String> getErrors(){
        return errors;
    }
}
