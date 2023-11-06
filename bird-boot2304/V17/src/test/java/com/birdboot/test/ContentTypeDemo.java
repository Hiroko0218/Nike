package com.birdboot.test;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;

/**
 * 使用 MimetypesFileTypeMap来分析不同文件對應的 Content-Type值
 */
public class ContentTypeDemo {
    public static void main(String[] args) {
        MimetypesFileTypeMap mftm = new MimetypesFileTypeMap();

        File file = new File("dome.css");
        String contentType = mftm.getContentType(file);
        System.out.println(contentType);
    }
}
