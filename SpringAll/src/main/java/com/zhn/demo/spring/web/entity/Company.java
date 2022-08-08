package com.zhn.demo.spring.web.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public class Company extends AccessToken {

    // 公司名称
    @NotBlank(message = "名称不能为空")
    private String name;
    // 公司所在城市
    private String city;
    // 公司老板
    @NotNull(message = "老板不能null")
    private String boss;
    // 公司联系电话
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1([34578])\\d{9}$", message = "手机号码格式错误")
    private String tel;
    // 公司注册金额
    @Min(value = 0, message = "注册金额不能小于零")
    private int amount;
    // 公司部门
    private List<Department> departments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", boss='" + boss + '\'' +
                ", tel='" + tel + '\'' +
                ", amount=" + amount +
                ", departments=" + departments +
                '}';
    }
}
