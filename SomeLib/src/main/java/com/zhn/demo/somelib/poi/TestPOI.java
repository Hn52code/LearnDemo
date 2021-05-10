package com.zhn.demo.somelib.poi;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class TestPOI {

    public static void main(String[] args) throws Exception {

        List<People> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            People people = new People();
            people.setId(i);
            people.setAddr("我来自派安--" + i);
            people.setAge(10 + i);
            people.setName("名字");
            if (i % 2 == 0)
                people.setSex("男");
            else
                people.setSex("女");
            list.add(people);
        }

        File file = new File("e:\\text.xlsx");
        if (!file.exists())
            file.createNewFile();

        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));

        new ExcelUtil<>(People.class).exportExcel(list, "test", 0,outputStream);

        outputStream.close();
    }
}
