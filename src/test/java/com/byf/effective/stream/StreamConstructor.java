package com.byf.effective.stream;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 流的四种构建形式
 */
public class StreamConstructor {
    /**
     * 由数值直接构建流
     */
    @Test
    public void streamFromValue(){
        Stream stream = Stream.of(1,2,3,4,5);
        stream.forEach(System.out::println);
    }

    /**
     * 通过数组生成流
     */
    @Test
    public void streamFromArray(){
        int[] numbers = {1,2,3,4,5};
        IntStream stream = Arrays.stream(numbers);
        stream.forEach(System.out::println);
    }

    /**
     * 通过文件生成流
     * @throws IOException
     */
    @Test
    public void streamFromFile() throws IOException {
        Stream<String> stream = Files.lines(
                Paths.get("G:\\Effective_Code\\effective_code\\src\\test\\java\\com\\byf\\effective\\stream\\StreamConstructor.java")
        );
        stream.forEach(System.out::println);
    }

    /**
     * 通过函数生成流：无限流
     */
    @Test
    public void streamFromFunction(){
        Stream stream = Stream.iterate(0,n-> n+2);
        Stream stream1 = Stream.generate(Math::random);
        stream1.limit(100).forEach(System.out::println);

    }
}
