package com.zhn.demo.basic.current;

import java.util.HashMap;
import java.util.Map;

/**
 * 为验证 在finally{}前，先返回值，然后在该块中将全局变量回复原始阶段，实验结果是返回应用对象后结果不被finally{}所影响
 */
public class App {

    public static void main(String[] args) {
//        System.out.println(method1());    // error
//        System.out.println(name);         // null

        System.out.println(method2());       // error
        System.out.println(map.toString());  // null


    }

    static String name = null;

    static Map<String, Object> map = null;

    static String method1() {
        try {
            System.out.println(1 / 0);
            name = "正常";
            return name;
        } catch (Exception e) {
            return "error";
        } finally {
            name = null;
        }
    }

    static Map<String, Object> method2() {
        map = new HashMap<>();
        try {
            System.out.println(1 / 0);
            map.put("result", "success");
            return map;
        } catch (Exception e) {
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("result", "error");
            return map1;
        } finally {
            map = null;
        }
    }
}
