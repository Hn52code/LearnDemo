package com.zhn.demo.somelib.xml.jaxb;

import com.zhn.demo.somelib.xml.jaxb.pojo.WxMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public class XmlBeanDemo {


    public static void main(String[] args) throws JAXBException {

        /* 将xml字符串转为Javabean */
        String xmlStr = "<xml><ToUserName><![CDATA[gh_e5866411eb06]]></ToUserName>\n" +
                "<FromUserName><![CDATA[o8bJ10kNv9OS2_e0JfB4b_Ls9-hk]]></FromUserName>\n" +
                "<CreateTime>1571732575</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[权威]]></Content>\n" +
                "<MsgId>22501800721477919</MsgId>\n" +
                "</xml>";
        JAXBContext context = JAXBContext.newInstance(WxMessage.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader sr = new StringReader(xmlStr);
        WxMessage wxMessage = (WxMessage) unmarshaller.unmarshal(sr);
        System.out.println(wxMessage.toString());

        /* 将Javabean转为xml字符串 */

        Marshaller marshaller = JAXBContext.newInstance(WxMessage.class).createMarshaller();
        // 格式化输出
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // 设置编码
        marshaller.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.name());
        // 去除声明部分
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(wxMessage, writer);
        String xmlStr2 = writer.toString();
        System.out.println(xmlStr2);
    }
}
