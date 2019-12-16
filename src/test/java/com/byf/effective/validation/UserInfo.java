package com.byf.effective.validation;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.util.Date;
import java.util.List;

/**
 * 待验证对象实体类
 * 用户信息类‘
 *
 *
 */
@Data
public class UserInfo {

    public UserInfo(){

    }

    // 登录场景
    public interface LoginGroup {}

    // 注册场景
    public interface RegisterGroup {}

    // 排序场景
    @GroupSequence({
            LoginGroup.class,
            RegisterGroup.class,
            Default.class //属于默认属性，即没有指明验证组的属性
    })
    public interface Group {}
    // 用户ID
    @NotNull(message = "用户ID不能为空",
            groups = LoginGroup.class)
    private String userId;

    //用户名
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    /**
     * 用户密码
     * NotBlank 自动去掉字符串前后的空格后验证是否为空
      */
    @NotBlank(message = "用户密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度不能少于6位，不能多于20位")
    private String password;

    // 邮箱
    @NotNull(groups = RegisterGroup.class,
            message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    // 手机号
    @Phone(message = "手机号不是187开头")
    private String phone;

    // 生日
    @Past(message = "生日不能为未来时间点")
    private Date birthday;

    // 年龄
    @Min(value = 18, message = "年龄不能小于18岁")
    @Max(value = 60, message = "年龄不能大于60岁")
    private Integer age;

    /**
     * 好友列表
     * @Valid 级联验证
     */
    @Size(min = 1, message = "不能少于1个好友")
    private List<@Valid UserInfo> friends;

}
