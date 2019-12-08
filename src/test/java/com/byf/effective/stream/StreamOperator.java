package com.byf.effective.stream;

import com.alibaba.fastjson.JSON;
import com.byf.effective.lamba.cart.CartService;
import com.byf.effective.lamba.cart.Sku;
import com.byf.effective.lamba.cart.SkuCategoryEnum;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * 演示流的各种操作
 */
public class StreamOperator {
    List<Sku> list;

    @Before
    public void init(){
        list = CartService.getCartSkuList();
    }

    /**
     * filter使用：过滤到不符合断言判断的数据
     */
    @Test
    public void filterTest(){
        list.stream()
                .filter(sku -> SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory()))
                .forEach(item -> System.out.println(JSON.toJSONString(item,true)));
    }

    /**
     * map使用：将一个元素转换为另一个元素
     */
    @Test
    public void mapTest(){
        list.stream()
                .map(sku -> sku.getSkuName())
                .forEach(item -> System.out.println(JSON.toJSONString(item, true)));
    }

    /**
     * flatMap使用：将一个元素转换为另外一个流
     */
    @Test
    public void filterMap(){
        list.stream()
                .flatMap(sku -> Arrays.stream(sku.getSkuName().split("")))
                .forEach(item-> System.out.println(JSON.toJSONString(item, true)));
    }

    /**
     * peek:对流中的元素进行遍历操作，与foreach类似，但不会销毁流元素
     */
    @Test
    public void peek(){
        list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                .forEach(item-> System.out.println(JSON.toJSONString(item, true)));
    }

    /**
     * sort：对流总元素进行排序，可选择自然排序或指定排序规则
     * 有状态操作
     */
    @Test
    public void sortTest(){
        list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                // 有状态操作，peek就无状态需要先完成
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                .forEach(item-> System.out.println(JSON.toJSONString(item, true)));
    }

    /**
     * distinct：对流元素进行去重
     * 有状态操作
     */
    @Test
    public void distinctTest(){
        list.stream()
                .map(sku -> sku.getSkuCategory())
                .distinct()
                .forEach(item-> System.out.println(JSON.toJSONString(item,true)));
    }

    /**
     * skip:跳过前n条数据
     * 有状态操作
     */
    @Test
    public void skipTest(){
        list.stream()
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                .skip(3)
                .forEach(item-> System.out.println(JSON.toJSONString(item,true)));
    }

    /**
     * limit：截断前n条数据
     * 有状态操作
     */
    @Test
    public void limitTest(){
        list.stream()
                .sorted(Comparator.comparing(Sku::getTotalPrice))
                .skip(1 * 3)
                .limit(3)
                .forEach(item-> System.out.println(JSON.toJSONString(item, true)));
    }

    /**
     * allMatch使用：终端操作，短路操作，所有元素都匹配返回true
     */
    @Test
    public void allMatchTest(){
        boolean match = list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                .allMatch(sku -> sku.getTotalPrice() > 100);
        System.out.println(match);
    }

    /**
     * anyMatch:任何元素匹配返回true
     */
    @Test
    public void anyMatchTest(){
        boolean match = list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                .anyMatch(sku -> sku.getTotalPrice() > 100);
        System.out.println(match);
    }

    /**
     * noneMatch：任何元素都不匹配返回true
     */
    @Test
    public void noneMatchTest(){
        boolean match = list.stream()
                .peek(sku -> System.out.println(sku.getSkuName()))
                .noneMatch(sku -> sku.getTotalPrice() > 10000);
        System.out.println(match);
    }

    /**
     * findFirst：查找第一个
     * 短路操作
     */
    @Test
    public void findFirstTest(){
        Optional<Sku> optional = list.stream()
                .findFirst();
        System.out.println(JSON.toJSONString(optional.get(),true));
    }

    /**
     * 查找任意一个
     * 串行操作时，与findFirst没有区别
     */
    @Test
    public void findAnyTest(){
        Optional<Sku> optional = list.stream()
                .findAny();
        System.out.println(JSON.toJSONString(optional.get(),true));
    }

    /**
     * max使用
     */
    @Test
    public void maxTest(){
        OptionalDouble optionalDouble = list.stream()
                .mapToDouble(Sku::getTotalPrice)
                .max();
        System.out.println(optionalDouble.getAsDouble());
    }

    /**
     * min使用
     */
    @Test
    public void minTest(){
        OptionalDouble optionalDouble = list.stream()
                .mapToDouble(Sku::getTotalPrice)
                .min();
        System.out.println(optionalDouble.getAsDouble());
    }

    /**
     * 计数
     */
    @Test
    public void countTest(){
        long count = list.stream()
                .count();
        System.out.println(count);
    }
}
