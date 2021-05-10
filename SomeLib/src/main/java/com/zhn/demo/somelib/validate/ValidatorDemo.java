package com.zhn.demo.somelib.validate;

public class ValidatorDemo {

    public static void main(String[] args) {
        People people = new People();
        ValidatorUtil.ValidResult validResult = ValidatorUtil.validateBean(people);
        if (validResult.hasErrors()) {
            System.out.println(validResult.getErrors());
        }

    }
}
