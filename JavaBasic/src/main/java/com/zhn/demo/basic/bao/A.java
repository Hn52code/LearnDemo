package com.zhn.demo.basic.bao;

public class A {
    /* 权限修饰符               权限上线描述      */
    private String a1;      // 本类访问
    String a2;              // 本包访问 --- 缺省
    protected String a3;    // 本包/所有子类
    public String a4;       // 所有类

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA1() {
        return a1;
    }
}
