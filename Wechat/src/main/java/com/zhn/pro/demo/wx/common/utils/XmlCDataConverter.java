package com.zhn.pro.demo.wx.common.utils;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * @author ZhouHN
 * @desc XML工具中用于标识CData数据元素的转换器
 * @date 9:29 2019/10/26 0026
 */
public class XmlCDataConverter implements Converter {

    private static final String prefix = "<![CDATA[";
    private static final String suffix = "]]>";

    @Override
    public void marshal(Object o, HierarchicalStreamWriter writer, MarshallingContext context) {
        writer.setValue(prefix + o + suffix);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        return reader.getValue();
    }

    @Override
    public boolean canConvert(Class clazz) {
        return String.class.isAssignableFrom(clazz);
    }

}
