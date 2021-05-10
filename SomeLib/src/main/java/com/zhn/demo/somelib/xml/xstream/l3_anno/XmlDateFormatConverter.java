package com.zhn.demo.somelib.xml.xstream.l3_anno;

import com.thoughtworks.xstream.converters.basic.DateConverter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class XmlDateFormatConverter extends DateConverter {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String toString(Object obj) {
        Date date = (Date) obj;
        return simpleDateFormat.format(date);
    }
}
