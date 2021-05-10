package com.zhn.pro.demo.wx.common.utils;

import com.thoughtworks.xstream.XStream;
import org.apache.commons.lang.StringEscapeUtils;

import java.io.InputStream;

/**
 * @author ZhouHN
 * @desc Java与XML序列化与反序列化工具
 * @date 9:28 2019/10/26 0026
 */
public class XmlUtil {

    private static XStream newInstance() {
        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        return xStream;
    }

    public static String toXml(Object obj) {
        XStream xs = newInstance();
        xs.processAnnotations(obj.getClass());
        return StringEscapeUtils.unescapeXml(xs.toXML(obj));
    }

    public static Object toJavaObj(String xml, Class clazz) {
        XStream xs = newInstance();
        XStream.setupDefaultSecurity(xs);
        xs.allowTypes(new Class[]{clazz});
        xs.processAnnotations(clazz);
        return xs.fromXML(xml);
    }

    public static <T> T toJavaObj(InputStream xml, Class<T> clazz) {
        XStream xs = newInstance();
        XStream.setupDefaultSecurity(xs);
        xs.allowTypes(new Class[]{clazz});
        xs.processAnnotations(clazz);
        return (T) xs.fromXML(xml);
    }
}


