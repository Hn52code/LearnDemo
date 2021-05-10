package com.zhn.demo.somelib.xml.xstream.l4_converter;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * @author ZhouHN
 * @desc 自定义的实体转化器
 * @date 19:13 2019/10/24 0024
 */
public class XmlPeopleConverter implements Converter {

    @Override
    public void marshal(Object o, HierarchicalStreamWriter writer, MarshallingContext context) {
        People people = (People) o;
//        writer.startNode("prefix");
        writer.setValue(""+people.getName()+"");
        writer.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {

        People people = new People();
        reader.moveDown();
        people.setName(reader.getValue());
        reader.moveUp();
        return people;
    }

    @Override
    public boolean canConvert(Class clazz) {
        return clazz.equals(People.class);
    }
}
