package com.zhn.demo.mode.prototype;

public class Client {

    public static void main(String[] args) {

        // 传统方式
        Car car = new Car("小车", "宝马", "深圳");

        car.setInfo(new Info("这是一台宝马车"));

        Car car2 = new Car("小车", "宝马", "深圳");
        Car car3 = new Car("小车", "宝马", "深圳");
        Car car4 = new Car("小车", "宝马", "深圳");
        System.out.println(car);
        System.out.println(car2);
        System.out.println(car3);
        System.out.println(car4);

        System.out.println("==========原型模式==============");

        // 原型模式 方式
        Car car5 = (Car) car.clone();

        System.out.println(car5 == car); // false 虽然是克隆的方式，但是却并不是同一个对象

        car5.setType("大车");
        System.out.println(car);
        System.out.println(car5);

        System.out.println("==========浅拷贝==============");
        car5.getInfo().setDistribute("这是一台奔驰车");
        System.out.println(car);
        System.out.println(car5);

        System.out.println("========================");
        People people = new People("xiaozhou ", 24, true, new OtherInfo("湖南"));
        People people2 = (People) people.clone();
        System.out.println(people);
        System.out.println(people2);
        System.out.println("==========方式1 深拷贝==============");
        people2.getOtherInfo().setSchool("湖北");
        System.out.println(people);
        System.out.println(people2);

        System.out.println("==========方式2 深拷贝==============");
        People people3 = people.deepClone();
        people3.getOtherInfo().setSchool("广东");
        System.out.println(people);
        System.out.println(people3);

    }

}
