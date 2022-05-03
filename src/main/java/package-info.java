@XmlSchema(
        namespace = "http://univrouen.fr/rss22",
        elementFormDefault = XmlNsForm.QUALIFIED,
        xmlns = {
                @XmlNs(prefix="rss", namespaceURI="http://univrouen.fr/rss22")
        }
)
package fr.univrouen.rss22_project.model.xml;
import javax.xml.bind.annotation.*;