package com.byf.effective.resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyTest {
    /**
     * 1.创建输入/输出流
     * 2.执行文件拷贝，读取文件内容，写入到另一个文件
     * 3.关闭文件流资源
     */
    public void copyFile(){
        String oriUrl = "origin/FileCopyTest.java";
        String targetUrl = "origin/target/target.txt";

        FileInputStream originalFIS = null;
        FileOutputStream targetFOS = null;
        try {
            originalFIS = new FileInputStream(oriUrl);
            targetFOS = new FileOutputStream(targetUrl);

            int content;

            while((content = originalFIS.read()) != -1){
                targetFOS.write(content);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (targetFOS != null){
                try {
                    targetFOS.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (originalFIS != null){
                try {
                    originalFIS.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
