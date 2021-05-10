package com.zhn.demo.somelib.xml.xstream.last_zcode.way2;

import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;

public class MyXppDriver extends XppDriver {

    public MyXppDriver() {
        super();
    }

    public MyXppDriver(NameCoder nameCoder) {
        super(nameCoder);
    }

    @Override
    public HierarchicalStreamWriter createWriter(Writer out) {
        return new MyPrePrintWriter(out, super.getNameCoder());
    }
}
