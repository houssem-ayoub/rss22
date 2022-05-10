package fr.univrouen.rss22_project.model.adapter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static org.joda.time.DateTimeFieldType.*;

@Converter(autoApply = true)
public class ORMDateTimeConverter implements AttributeConverter<DateTime,String> {
    @Override
    public String convertToDatabaseColumn(DateTime dateTime) {
        final DateTimeFormatter df = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return dateTime.toString(df);
    }

    @Override
    public DateTime convertToEntityAttribute(String s) {
        final DateTimeFormatter df = getDateTimeFormatter();
        return df.parseDateTime(s);
        //return  df.parseBest(s)
    }

    private DateTimeFormatter getDateTimeFormatter(){
        final DateTimeParser msParser = new DateTimeFormatterBuilder()
                .appendLiteral('.').appendDecimal(millisOfSecond(), 1, 3)
                .toParser();
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        builder = builder.appendFixedDecimal(year(), 4)
                .appendLiteral('-')
                .appendFixedDecimal(monthOfYear(), 2)
                .appendLiteral('-')
                .appendFixedDecimal(dayOfMonth(), 2)
                .appendLiteral(' ')
                .appendFixedDecimal(hourOfDay(), 2)
                .appendLiteral(':')
                .appendFixedDecimal(minuteOfHour(), 2)
                .appendLiteral(':')
                .appendFixedDecimal(secondOfMinute(), 2)
                .appendOptional(msParser);
        return builder.toFormatter();
    }
}
