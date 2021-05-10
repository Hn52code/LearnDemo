package com.zhn.demo.somelib.xml.xstream.last_zcode.way2;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NameCoder;
import com.thoughtworks.xstream.io.naming.NoNameCoder;

public class XStreamFactory {


    public static XStream newInstance() {
        final NameCoder nameCoder = new NoNameCoder();
        return new XStream(new MyXppDriver(nameCoder));
    }

}
