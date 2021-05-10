package com.zhn.demo.somelib.xml.xstream.l3_anno;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class MyConverter implements Converter {

    private static final String prefix = "<![CDATA[";
    private static final String suffix = "]]>";

    @Override
    public void marshal(Object o, HierarchicalStreamWriter writer, MarshallingContext context) {
        writer.setValue(prefix + o.toString() + suffix);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        return null;
    }

    @Override
    public boolean canConvert(Class clz) {
        return clz.equals(String.class);
    }
}
