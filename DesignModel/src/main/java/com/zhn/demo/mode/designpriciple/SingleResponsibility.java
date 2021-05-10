package com.zhn.demo.mode.designpriciple;

/**
 * @author ZhouHN
 * @desc 单一职责 原则
 * @date 16:34 2019/12/6 0006
 */
public class SingleResponsibility {

    public static void main(String[] args) {

        // 单一模式之前
        Vehicle vehicle = new Vehicle();
        vehicle.run("汽车");
        vehicle.run("轮船");
        vehicle.run("飞机");


    }

}

// 分析
class Vehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " 在公路上跑");
    }
}
