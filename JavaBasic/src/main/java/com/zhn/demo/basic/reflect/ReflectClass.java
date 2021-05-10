package com.zhn.demo.basic.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectClass {

    /**
     * 获取Class对象的方式
     * 1.Class.forName("全类名")：将字节码文件加载进来，返回Class对象
     * 主要用于加载配置文件
     * 2.类名.class：通过类名的属性class属性获取
     * 主要用于传参
     * 3.对象.getClass():该方法位于Object类中。
     * 主要用于对象获取字节码的方式
     * <p>
     * 不管哪种方式获取的Class对象，他们都是相等。
     * 结论：同一个字节码文件（*.class）在一次程序中，仅仅只加加载一次，无论哪种方式获取同一个Class对象都是同一个
     * <p>
     * 使用Class对象：
     * 获取功能：
     * 1.获取成员变量
     * 2.获取构造方法
     * 3.获取成员方法
     * 4.获取类名
     * Field类操作：
     * 1.成员变量值设置
     * 2.成员变量值获取
     * 3.设置忽略安全申明（忽视修饰符）
     * <p>
     * Constructor类
     * 1.创建对象 该类提供创建方法
     * 2.Class提供创建方法
     * <p>
     * Method类
     * 1.获取方法
     * 2.调用方法
     */
    public static void main(String[] args) throws Exception {

        // 首先 获取Class 对象
        Class cls = Class.forName("com.zhn.pro.demo.basics.reflect.Lamp");
        // 其次 测试获取  field
        Field[] fields = cls.getFields();

        for (Field field : fields) {
            System.out.println(field);
        }
        Field field = cls.getDeclaredField("name");
        field.setAccessible(true);
        System.out.println(field.get(new People()));

        Method method = cls.getMethod("getAge");
        System.out.println(method.invoke(new People()));
    }

    void know() throws Exception {
        // 方式一
        Class clazz = Class.forName("com.zhn.pro.demo.basics.reflect.Lamp");
        System.out.println(clazz);
        // 方式二
        Class clazz2 = People.class;
        System.out.println(clazz2);
        // 方式三
        Class clazz3 = new People().getClass();
        System.out.println(clazz3);

        System.out.println(clazz == clazz2); // true
        System.out.println(clazz2 == clazz3);// true

        System.out.println("-----------------分割线-------------------");

        // 获取成员变量
        Field[] fields = clazz.getFields();
        Field fieldName = clazz.getField("name");
        // 获取构造方法
        Constructor[] constructors = clazz.getConstructors();
        Constructor constructor = clazz.getConstructor();
        // 获取成员方法
        Method[] methods = clazz.getMethods();
        clazz.getMethod("getAge");
        // 获取类名
        String name = clazz.getName();
    }
}
