package com.zhn.demo.webexample.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel
@Data
public class PersonDto {

    /** @ApiModelProperty 注解说明
     * value–字段说明
     * name–重写属性名字
     * dataType–重写属性类型
     * required–是否必填
     * example–举例说明
     * hidden–隐藏
     */

    @NotNull(message = "不能为空", groups = {Login.class})
    private int id;

    @ApiModelProperty(value = "不能为空",example ="xiaozhou")
    @NotNull(message = "名称不能为空", groups = {Register.class})
    private String name;
    @NotNull(message = "密码不能为空", groups = {Login.class, Register.class})
    private String pwd;
    private String address;

    public interface Register {
    }

    public interface Login {
    }

}
