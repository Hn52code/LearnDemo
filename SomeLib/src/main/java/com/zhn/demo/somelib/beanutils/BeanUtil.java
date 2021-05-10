package com.zhn.demo.somelib.beanutils;

import lombok.Data;
import net.sf.cglib.beans.BeanCopier;
import org.junit.Test;

import java.util.Date;

public class BeanUtil {


    /**
     * 原类和目标类，字段和字段类型一样，就会copy，切字段必须有对应的getter和setter方法
     * 如果原类字段类型是int，目标类是Integer 也不会copy，
     */

    /* A B字段一样 */
    @Test
    public void test1() {
        A a = new A();
        a.setAge(1);
        a.setName("zhou");
        final BeanCopier copier = BeanCopier.create(A.class, B.class, false);
        B b = new B();
        copier.copy(a, b, null);
        System.out.println(b);

    }

    /* A 字段少于 C（C 字段包含 A所有的） */
    @Test
    public void test2() {
        A a = new A();
        a.setAge(1);
        a.setName("zhou");
        final BeanCopier copier = BeanCopier.create(A.class, C.class, false);
        C c = new C();
        copier.copy(a, c, null);
        System.out.println(c);

    }

    /* A 和 D 有相同字段*/
    @Test
    public void test3() {
        A a = new A();
        a.setAge(1);
        a.setName("zhou");
        final BeanCopier copier = BeanCopier.create(A.class, D.class, false);
        D d = new D();
        copier.copy(a, d, null);
        System.out.println(d);

    }


    /* A 和 E 有相同字段 但类型不一样*/
    @Test
    public void test4() {
        A a = new A();
        a.setAge(1);
        a.setName("zhou");
        final BeanCopier copier = BeanCopier.create(A.class, E.class, false);
        E e = new E();
        copier.copy(a, e, null);
        System.out.println(e);

    }
}

@Data
class A {

    private String name;
    private int age;

}

@Data
class B {

    private String name;
    private int age;

}

@Data
class C {

    private String name;
    private int age;
    private Date date;

}

@Data
class D {

    private int age;
    private Date date;

}

@Data
class E {

    private Integer age;

}