package com.byf.effective.guava;

import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * JAVA8中的Optional使用方法
 */
public class OptionalTest {
    @Test
    public void test() throws Throwable {
        /**
         * 三种创建Optional对象方式
         */
        // 1.创建空的Optional对象
        Optional.empty();

        // 2.使用非null值创建Optional对象
        Optional.of("");

        // 3.使用任意值创建Optional对象
        Optional optional = Optional.ofNullable(null);

        /**
         * 判断是否引用确实的方法
         */
        optional.isPresent();

        /**
         * 当optional引用存在时执行
         * 类似的方法：map filter flatMap
         */
        optional.ifPresent(System.out::println);

        /**
         * 当optional引用缺失时执行
         */
        optional.orElse("引用缺失");
        optional.orElseGet(()->{
            return "自定义引用缺失";
        });
        /*optional.orElseThrow(()->{
            throw new RuntimeException("引用缺失异常");
        });*/
    }

    public static void stream(List<String> list){
        //list.stream().forEach(System.out::println);
        Optional.ofNullable(list)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        stream(null);
    }
}
