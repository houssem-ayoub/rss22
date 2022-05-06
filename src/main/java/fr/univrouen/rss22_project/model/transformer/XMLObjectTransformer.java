package fr.univrouen.rss22_project.model.transformer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

@SuppressWarnings("rawtypes")
public class XMLObjectTransformer {
    public String toXMLString(Class clazz, Object objectXML){
       try {
           JAXBContext context = JAXBContext.newInstance(clazz);
           Marshaller marshaller = context.createMarshaller();
           marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
           marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
           StringWriter stringWriter = new StringWriter();
           marshaller.marshal(objectXML,stringWriter);
           return stringWriter.toString();
       }
       catch (JAXBException e){
            e.printStackTrace();
       }
       return "";
    }
    public String toHTMLString(Class clazz, String xsltFileName, Object XMLData){
        StringWriter sw = new StringWriter();
        try {
        Source xslt = new StreamSource("src/main/resources/xml/"+xsltFileName+".xslt");
        String xml = toXMLString(clazz,XMLData);
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transform = tFactory.newTransformer(xslt);
        transform.transform(new StreamSource(new StringReader(xml)),new StreamResult(sw));

    } catch (TransformerFactoryConfigurationError | TransformerException e) {
        e.printStackTrace();
    }
        return sw.toString();
    }
}
