package com.zhn.demo.mode.prototype;

public class Car implements Cloneable {

    private String type;    // 车的类型
    private String brand;   // 车的品牌
    private String produce; // 车的产地

    private Info info;

    public Car(String type, String brand, String produce) {
        this.type = type;
        this.brand = brand;
        this.produce = produce;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProduce() {
        return produce;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }

    @Override
    public String toString() {
        return "Car{" +
                "type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", produce='" + produce + '\'' +
                ", info=" + info +
                '}';
    }

    @Override
    protected Object clone() {
        Car car = null;
        try {
            car = (Car) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return car;
    }
}
