package com.zhn.demo.webexample.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class UserDto {

    // @ApiModelProperty(value = "学生姓名", example = "xiaozhou", notes = "长度在5到20之间", required = true)
    @NotBlank(message = "不能为空")
    @Length(min = 5, max = 20, message = "name长度必须在5到20之间")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9\\*]*$", message = "用户昵称限制：最多20字符，包含文字、字母和数字")
    private String name;    // 名称

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String mobile;  // 电话

    @Email
    private String email;   // 邮箱

    @Min(value = 18, message = "年龄必须大于等于18")
    private int age;        // 年龄

    @Min(value = 1, message = "级别必须大于等于1")
    @Max(value = 3, message = "级别必须小于等于3")
    private int level;     // 级别

    @Past(message = "时间必须是过去")
    private Date birthday;

    @Future(message = "时间必须是未来")
    private Date createTime;  // 时间

}
