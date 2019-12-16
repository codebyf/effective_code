package com.byf.effective.validation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

/**
 * 验证测试类
 */
public class ValidationTest {
    // 验证器对象
    private Validator validator;
    // 待验证对象
    private UserInfo userInfo;
    //验证结果集合
    private Set<ConstraintViolation<UserInfo>> set;

    //验证结果集合
    private Set<ConstraintViolation<UserInfoService>> set1;


    /**
     * 初始化操作
     */
    @Before
    public void init(){
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        userInfo = new UserInfo();
        //userInfo.setUserId("123456");
        userInfo.setUserName("BYF");
        userInfo.setPassword("123456");
        userInfo.setEmail("baiyifan@163.com");
        userInfo.setAge(18);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019,1,1);
        userInfo.setBirthday(calendar.getTime());
        userInfo.setPhone("18705193697");

        UserInfo friend = new UserInfo();
        //friend.setUserId("234566");
        friend.setUserName("HELLO");
        friend.setPassword("222333444");
        friend.setPhone("18705193697");
        //friend.setEmail("hello@163.com");
        userInfo.setFriends(new ArrayList(){{add(friend);}});
    }

    @Test
    public void test(){
        set = validator.validate(userInfo);
    }

    /**
     * 级联验证测试
     */
    @Test
    public void graphValidation(){
        set = validator.validate(userInfo);
    }

    /**
     * 分组验证
     */
    @Test
    public void groupValidation(){
        set = validator.validate(userInfo,
                UserInfo.RegisterGroup.class,
                UserInfo.LoginGroup.class);
    }

    /**
     * 组序列验证
     */
    @Test
    public void groupSequenceValidation(){
        set = validator.validate(userInfo, UserInfo.Group.class);
    }

    @After
    public void print(){
        set.stream().forEach(item -> {
            System.out.println(item.getMessage());
        });
        /*set1.stream().forEach(item -> {
            System.out.println(item.getMessage());
        });*/
    }

    /**
     * 对方法入参进行校验
     * @throws NoSuchMethodException
     */
    @Test
    public void paramValidation() throws NoSuchMethodException {
        ExecutableValidator executableValidator =
                validator.forExecutables();
        // 待验证对象
        UserInfoService service = new UserInfoService();
        // 待验证方法
        Method method = service.getClass()
                .getMethod("setUserInfo", UserInfo.class);
        // 方法输入参数
        Object[] paramObjects = new Object[]{new UserInfo()};

        // 对方法的输入参数进行验证
        set1 = executableValidator.validateParameters(
                service,
                method,
                paramObjects
        );
    }

    /**
     * 对返回值进行约束校验
     */
    @Test
    public void returnValueValidation() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 获取校验执行器
        ExecutableValidator executableValidator = validator.forExecutables();
        // 构造验证的方法对象
        UserInfoService service = new UserInfoService();
        Method method = service.getClass()
                .getMethod("getUserInfo");
        // 调用方法得到返回值
        Object returnValue = method.invoke(service);
        // 校验方法返回值是否符合约束
        set1 = executableValidator.validateReturnValue(
                service,
                method,
                returnValue);

    }

    /**
     * 对构造函数输入进行校验
     * @throws NoSuchMethodException
     */
    @Test
    public void constructorValidation() throws NoSuchMethodException {
        // 获取验证执行器
        ExecutableValidator executableValidator = validator.forExecutables();
        // 获取构造函数
        Constructor constructor = UserInfoService.class.getConstructor(UserInfo.class);
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("baiyifan@163.com@");
        Object[] paramObjects = new Object[]{userInfo};
        // 校验构造函数
        set1 = executableValidator.validateConstructorParameters(
                constructor,
                paramObjects
        );
    }
}
