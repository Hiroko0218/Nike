package com.birdboot.test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        String str = "范";
        byte[] data = str.getBytes(StandardCharsets.UTF_8);
        System.out.println(Arrays.toString(data));
    }
}
