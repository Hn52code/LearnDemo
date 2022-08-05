package com.zhn.demo.basic.jdk8.map;

import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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

    @Test
    public void testMapFilter() {
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("1", "1");
        map.put("2", "1");
        map.put("3", "1");
        map.put("4", "1");
        // 使用filter后 map 本身不会被过滤，需要过滤后的数据需重新指向
        map.entrySet()
                .stream()
                .filter(entry -> !entry.getKey().equals("2"))
                .forEach(entry -> System.out.println(entry.getKey() + "_________" + entry.getValue()));
        map.entrySet()
                .stream()
                .filter(entry -> !entry.getKey().equals("2"))
                .collect(Collectors.toConcurrentMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(map.toString());
    }

}
