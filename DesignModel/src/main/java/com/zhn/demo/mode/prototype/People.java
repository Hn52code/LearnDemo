package com.zhn.demo.mode.prototype;

import java.io.*;

public class People implements Cloneable, Serializable {

    private String name;
    private int age;
    private boolean isMan;
    private OtherInfo otherInfo;

    public People(String name, int age, boolean isMan, OtherInfo otherInfo) {
        this.name = name;
        this.age = age;
        this.isMan = isMan;
        this.otherInfo = otherInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }

    public OtherInfo getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(OtherInfo otherInfo) {
        this.otherInfo = otherInfo;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isMan=" + isMan +
                ", otherInfo=" + otherInfo +
                '}';
    }

    // =========深拷贝==================
    // 方式一 引用类型的类要一一实现Cloneable，然后依赖的类在克隆方法要意义转换 如下
    @Override
    protected Object clone() {
        People people = null;
        try {
            people = (People) super.clone();
            people.otherInfo = (OtherInfo) people.otherInfo.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return people;
    }

    // 方式二 序列化
    public People deepClone() {
        ByteArrayOutputStream bo = null;
        ObjectOutputStream oo = null;
        ByteArrayInputStream bi = null;
        ObjectInputStream oi = null;
        try {
            // 序列化
            bo = new ByteArrayOutputStream();
            oo = new ObjectOutputStream(bo);
            oo.writeObject(this);  // 当前对象输出

            // 反序列化
            bi = new ByteArrayInputStream(bo.toByteArray());
            oi = new ObjectInputStream(bi);
            return (People) oi.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bo != null) bi.close();
                if (oo != null) bi.close();
                if (bi != null) bi.close();
                if (oi != null) bi.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
