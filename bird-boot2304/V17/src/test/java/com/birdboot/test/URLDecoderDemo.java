package com.birdboot.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 測試URLDecoder類
 */
public class URLDecoderDemo {
    public static void main(String[] args) {
        String line = "/loginUser?username=%E8%8C%83%E4%BC%A0%E5%A5%87&password=123456";

        try {
            line = URLDecoder.decode(line,"UTF-8");
            System.out.println(line);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

