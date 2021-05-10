package com.zhn.demo.basic.jdk8.map;

import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MapTest {

    @Test
    public void testMap() {
        Map<String, Object> map = new HashMap<>();
        Object name = map.getOrDefault("name", "zhouhainan");
        System.out.println(name);
        System.out.println(map.get("name"));

        System.out.println(map.putIfAbsent("age", 18));
        System.out.println(map.get("age"));

        System.out.println(map.computeIfAbsent("birthday", k -> 1534491235));
        System.out.println(map.computeIfAbsent("birthday", k -> 1349815687));
        System.out.println(map.get("birthday"));

    }

}
