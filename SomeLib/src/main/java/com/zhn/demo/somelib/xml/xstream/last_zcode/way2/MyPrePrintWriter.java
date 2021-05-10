package com.zhn.demo.somelib.xml.xstream.last_zcode.way2;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.naming.NameCoder;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;

import java.io.Writer;
import java.lang.reflect.Field;

public class MyPrePrintWriter extends PrettyPrintWriter {

    public static final String CDATA_PREFIX = "<![CDATA[";
    public static final String CDATA_SUFFIX = "]]>";

    private boolean isCDATA = true;
    private Class<?> targetClazz = null;

    public MyPrePrintWriter(Writer writer, NameCoder nameCoder) {
        super(writer, nameCoder);
    }

    @Override
    public void startNode(String name, Class clazz) {
        super.startNode(name, clazz);
//        if (targetClazz == null)
//            targetClazz = clazz;
//        isCDATA = needCDATA(clazz,name);
        // 设置默认字符串 转为 CDATA
        isCDATA = clazz == String.class;
    }

    @Override
    protected void writeText(QuickWriter writer, String text) {
        if (isCDATA) {
            writer.write(CDATA_PREFIX);
            writer.write(text);
            writer.write(CDATA_SUFFIX);
        } else {
            super.writeText(writer, text);
        }
    }


    private boolean needCDATA(Class<?> targetClass, String fieldAlias) {
        boolean cdata = false;
        //first, scan self
        cdata = existsCDATA(targetClass, fieldAlias);
        if (cdata) return cdata;
        //if cdata is false, scan supperClass until java.lang.Object
        Class<?> superClass = targetClass.getSuperclass();
        while (!superClass.equals(Object.class)) {
            cdata = existsCDATA(superClass, fieldAlias);
            if (cdata) return cdata;
            superClass = superClass.getSuperclass();
        }
        return false;
    }

    private boolean existsCDATA(Class<?> clazz, String fieldAlias) {
        //scan fields
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //1. exists XStreamCDATA
            if (field.getAnnotation(XmlCData.class) != null) {
                XStreamAlias xStreamAlias = field.getAnnotation(XStreamAlias.class);
                //2. exists XStreamAlias
                if (null != xStreamAlias) {
                    if (fieldAlias.equals(xStreamAlias.value()))//matched
                        return true;
                } else {// not exists XStreamAlias
                    if (fieldAlias.equals(field.getName()))
                        return true;
                }
            }
        }
        return false;
    }

}
