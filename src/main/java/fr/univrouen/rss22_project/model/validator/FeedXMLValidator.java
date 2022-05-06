package fr.univrouen.rss22_project.model.validator;

import fr.univrouen.rss22_project.model.xml.FeedXML;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.StringWriter;
import java.util.List;

public class FeedXMLValidator {
    private Schema schema;

    public FeedXMLValidator() {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            String xsdPath = "src/main/resources/xml/rss22.xsd";
            schema = schemaFactory.newSchema(new Source[]{new StreamSource(xsdPath)});
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
    public List<String> validate(FeedXML feedXML){
        try {
            FeedXMLValidationEventHandler feedXMLValidationEventHandler = new FeedXMLValidationEventHandler();
            JAXBContext context = JAXBContext.newInstance(FeedXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setSchema(schema);
            marshaller.setEventHandler(feedXMLValidationEventHandler);
            marshaller.marshal(feedXML,new StringWriter());
            return feedXMLValidationEventHandler.getErrors();

        }
        catch (JAXBException e){
            e.printStackTrace();
            return null;
        }

    }
}
