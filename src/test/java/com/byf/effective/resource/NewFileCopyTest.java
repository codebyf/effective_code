package com.byf.effective.resource;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * JDK1.7之后，实现正确关闭流资源方法
 * try - with - resource
 */
public class NewFileCopyTest {
    @Test
    public void copyFile(){
        // 定义输入输出路径
        String oriUrl = "origin/NewFileCopyTest.java";
        String targetUrl = "origin/target/target1.txt";

        // 初始化输入输出流对象
        try(
                FileInputStream originalFIS = new FileInputStream(oriUrl);
                FileOutputStream targetFOS = new FileOutputStream(targetUrl);
                ){
            int content;
            while((content = originalFIS.read()) != -1){
                targetFOS.write(content);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
