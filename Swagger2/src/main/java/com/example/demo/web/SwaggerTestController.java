package com.example.demo.web;

import com.example.demo.entity.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/swagger")
@Api(description = "这是一个controller API")
public class SwaggerTestController {

    @GetMapping("/get")
    @ApiOperation(value = "获取map集合", notes = "一个无参数接口")
    public Map<String, Object> get() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "zhouhainan");
        map.put("sex", "男");
        map.put("age", 24);
        return map;
    }

    /**
     * @Api：用在类上，说明该类的作用
     * @ApiOperation：用在方法上，说明方法的作用
     * @ApiImplicitParams：用在方法上包含一组参数说明
     * @ApiImplicitParam：用在 @ApiImplicitParams 注解中，指定一个请求参数的各个方面
     * paramType：参数放在哪个地方
     * · header --> 请求参数的获取：@RequestHeader
     * · query --> 请求参数的获取：@RequestParam
     * · path（用于restful接口）--> 请求参数的获取：@PathVariable
     * · body（不常用）
     * · form（不常用）
     * name：参数名
     * dataType：参数类型
     * required：参数是否必须传
     * value：参数的意思
     * defaultValue：参数的默认值
     * @ApiResponses：用于表示一组响应
     * @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息 code：数字，例如400
     * message：信息，例如"请求参数没填好"
     * response：抛出异常的类
     * @ApiModel：描述一个Model的信息（这种一般用在post创建的时候， 使用@RequestBody这样的场景，
     * 请求参数无法使用@ApiImplicitParam注解进行描述的时候）@ApiModelProperty：描述一个model的属性
     */

    /**
     * 参数类型为long 或者 int 等整数类型的参数时 example值不能缺失（如：example="0"），否则报错
     */

    /* 传参例子 */

    /* 参数 问号后键值对 */
    @GetMapping("/get2")
    @ApiOperation(value = "获取信息", notes = "带参接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", paramType = "query", required = true, dataType = "int", example = "0"),
            @ApiImplicitParam(name = "name", value = "名称", paramType = "query", required = true, dataType = "String")
    })
    public String get2(@RequestParam("id") int id, @RequestParam("name") String name) {
        return name + id;
    }

    /* 参数 问号后键值对 */
    @Deprecated
    @GetMapping("/get3")
    @ApiOperation(value = "获取信息", notes = "带参接口，已过期")
    @ApiImplicitParam(name = "id", value = "id", paramType = "query", required = true, dataType = "int", example = "0")
    public int get3(@RequestParam("id") int id) {
        return id;
    }

    /* 参数 实体实例 json式 */
    @PostMapping("/add")
    @ApiOperation(value = "添加一个用户", notes = "json参数")
    public User add(@RequestBody @ApiParam(name = "用户信息", value = "传入json格式") User user) {
        System.out.println(user.toString());
        return user;
    }

    /* 参数 实体实例 表单式 */
    @PutMapping("/update")
    @ApiOperation(value = "添加一个用户", notes = "表单参数")
    @ApiImplicitParam(name = "user", value = "用户信息", paramType = "form", dataType = "User")
    public int put(User user) {
        System.out.println(user.toString());
        return 1;
    }

    /* 参数 uri中参数 */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除", notes = "Rest Ful 风格参数")
    @ApiImplicitParam(name = "id", value = "id", paramType = "path", required = true, dataType = "int", example = "0")
    public int delete(@PathVariable("id") int id) {
        return 0;
    }


}
