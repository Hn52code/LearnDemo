package com.zhn.demo.somelib.xml.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.stream.XMLStreamWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CDataAdapter extends XmlAdapter<String, String> {

    @Override
    public String unmarshal(String v) throws Exception {
        return v;
    }

    @Override
    public String marshal(String v) throws Exception {
        return "<![CDATA[" + v + "]]>";
    }

    protected static CDataHandler newInstance(XMLStreamWriter writer) {
        return new CDataHandler(writer);
    }

    private static class CDataHandler implements InvocationHandler {
        private static Method writeCharactersMethod = null;

        static {
            try {
                writeCharactersMethod = XMLStreamWriter.class.getDeclaredMethod("writeCharacters", String.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        private XMLStreamWriter writer;

        public CDataHandler(XMLStreamWriter writer) {
            this.writer = writer;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (writeCharactersMethod.equals(method)) {
                String text = args[0].toString();
                if (text != null && text.startsWith("<![CDATA[") && text.endsWith("]]>"))
                    writer.writeCData(text.substring(9, text.length() - 3));
            }
            return method.invoke(writer, args);
        }
    }
}
