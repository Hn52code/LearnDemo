package com.zhn.demo.somelib.xml.xstream.last_zcode.way1;

import com.thoughtworks.xstream.converters.basic.StringConverter;

public class XmlCDataConverter extends StringConverter {

    private static final String prefix = "<![CDATA[";
    private static final String suffix = "]]>";

    @Override
    public String toString(Object obj) {
        return prefix + obj + suffix;
    }
}
