package com.zhn.demo.somelib.validate;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class People {


    private int id;
    @NotNull
    @Length(min = 5, max = 20)
    private String name;

    @NotNull
    @Min(18)
    private int age;

    @Min(1)
    @Max(3)
    private int level;

    private String phone;


    private int sex;

}
