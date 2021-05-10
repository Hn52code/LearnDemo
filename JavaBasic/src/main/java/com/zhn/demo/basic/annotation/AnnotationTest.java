package com.zhn.demo.basic.annotation;

import java.lang.annotation.*;
import java.lang.reflect.Method;

@MyClass("标注的类")
public class AnnotationTest {

    @MyField("标注的字段")
    private String noteField = "字段值";

    @MyMethod("标注的方法")
    public void noteMethod(@MyParameter("标注的参数") String notePara) {
        System.out.println("参数值：" + notePara);
    }

    public static void main(String[] args) throws Exception {

        /* 获取标注类的标注的值 */
        MyClass myClass = AnnotationTest.class.getAnnotation(MyClass.class);
        System.out.println("NoteClass标注的值：" + myClass.value());
        /* 获取标注类标注字段的标注值 */
        MyField myField = AnnotationTest.class.getDeclaredField("noteField").getAnnotation(MyField.class);
        System.out.println(myField.value());
        /* 获取标注类标注方法的标志值 */
        Method myMethod = AnnotationTest.class.getDeclaredMethod("noteMethod", String.class);
        MyMethod annotation = myMethod.getAnnotation(MyMethod.class);
        System.out.println("方法上的注解：" + annotation.value());
        /* 获取标注类标注方法的标注参数的标注值 */
        Annotation[][] parameterAnnotations = myMethod.getParameterAnnotations();
        for (Annotation[] parameterAnnotation : parameterAnnotations) {
            for (Annotation annotation1 : parameterAnnotation) {
                if (annotation1 instanceof MyParameter)
                    System.out.println("参数上的注解：" + ((MyParameter) annotation1).value());
            }
        }
    }

}

/**
 * 定义一个可以注解在 class interface enum 上的注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface MyClass {

    String value() default "可标注的在类上";

}

/**
 * 定义一个可以注解在 方法 上的注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyMethod {

    String value() default "可标注在方法上";

}

/**
 * 定义一个可以注解在 字段 上的注解
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyField {

    String value() default "可标注的在字段上";

}

/**
 * 定义一个可以注解在 参数 上的注解
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@interface MyParameter {

    String value() default "可标注在参数上";

}