package com.zhn.demo.somelib.xml.xstream.l4_converter;

import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

/**
 * @author ZhouHN
 * @desc 自定义的实体转化器
 * @date 19:13 2019/10/24 0024
 */
public class XmlPeople2Converter extends AbstractSingleValueConverter {

    @Override
    public Object fromString(String s) {
        return new People(s);
    }

    @Override
    public String toString(Object obj) {
        return super.toString(obj);
    }

    @Override
    public boolean canConvert(Class clazz) {
        return clazz.equals(People.class);
    }
}
