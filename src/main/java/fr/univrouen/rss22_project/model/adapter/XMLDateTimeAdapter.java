package fr.univrouen.rss22_project.model.adapter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class XMLDateTimeAdapter extends XmlAdapter<String, DateTime>{
    private final DateTimeFormatter df = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    @Override
    public DateTime unmarshal(String s) {
        String iso = s.replaceAll("^(\\d{4}-\\d{2}-\\d{2})\\s?[Tt]?([0-2]\\d:[0-5]\\d:[0-5]\\d.\\d\\d\\d)[Zz]?$","$1T$2Z");
            return df.parseDateTime(iso);
    }

    @Override
    public String marshal(DateTime dateTime) {
        return dateTime.toString(df);
    }
}
