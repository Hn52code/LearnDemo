package com.zhn.demo.somelib.xml.xstream.last_zcode.way3;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.enums.EnumConverter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class XmlEnumCDataConverter extends EnumConverter {

    private static final String prefix = "<![CDATA[";
    private static final String suffix = "]]>";

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        writer.setValue(prefix + ((Enum) source).name() + suffix);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        String value = reader.getValue();
        value = value.substring(0, value.length() - 3).substring(9);
        Class type = context.getRequiredType();
        if (type.getSuperclass() != Enum.class) {
            type = type.getSuperclass();
        }
        try {
            return Enum.valueOf(type, value);
        } catch (IllegalArgumentException var10) {
            Enum[] enums = (Enum[]) type.getEnumConstants();
            for (Enum c : enums) {
                if (c.name().equalsIgnoreCase(value)) {
                    return c;
                }
            }
            throw var10;
        }
    }
}
