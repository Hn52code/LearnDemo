package com.zhn.demo.somelib.xml.jaxb;

import com.zhn.demo.somelib.xml.jaxb.pojo.WxMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public class XmlBeanCDATADemo {


    public static void main(String[] args) throws JAXBException, XMLStreamException {

        /* 将Javabean转为xml字符串 */

        WxMessage wxMessage = new WxMessage();
        wxMessage.setContent("121312");
        wxMessage.setCreateTime("121312");
        wxMessage.setFromUserName("121312");
        wxMessage.setToUserName("121312");
        wxMessage.setMsgId("121312");
        wxMessage.setMsgType("121312");

        StringWriter writer = new StringWriter();
//        XMLStreamWriter streamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(writer);
//        XMLStreamWriter cdata = (XMLStreamWriter) Proxy.newProxyInstance(streamWriter.getClass().getClassLoader(), streamWriter.getClass().getInterfaces(), CDataAdapter.newInstance(streamWriter));

        Marshaller marshaller = JAXBContext.newInstance(WxMessage.class).createMarshaller();
        // 格式化输出
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // 设置编码
        marshaller.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.name());
        // 去除声明部分
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

        marshaller.marshal(wxMessage, writer);
        String xmlStr2 = writer.toString();
        System.out.println(xmlStr2);
    }
}
