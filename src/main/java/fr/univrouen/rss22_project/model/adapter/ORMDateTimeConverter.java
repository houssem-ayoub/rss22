package fr.univrouen.rss22_project.model.adapter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ORMDateTimeConverter implements AttributeConverter<DateTime,String> {
    private final DateTimeFormatter df = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
    @Override
    public String convertToDatabaseColumn(DateTime dateTime) {
        return dateTime.toString(df);
    }

    @Override
    public DateTime convertToEntityAttribute(String s) {
        return DateTime.parse(s,df);
    }
}
