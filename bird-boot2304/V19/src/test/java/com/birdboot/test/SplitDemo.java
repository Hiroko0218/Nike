package com.birdboot.test;

import java.util.Arrays;

/**
 * String提供了一個重載的split方法
 */
public class SplitDemo {
    public static void main(String[] args) {
        String line = "1=2=3=4=5=6=7=8=====";
        String[] data = line.split("=");
        System.out.println(Arrays.toString(data));
        //limit:拆分出的項的個數
        data = line.split("=",2);//按照"="僅拆分出兩項
        System.out.println(Arrays.toString(data));

        data = line.split("=",3);//按照"="僅拆分出三項
        System.out.println(Arrays.toString(data));

        //當limit>最大可拆分項時，將所有可拆分項全部保留
        data = line.split("=",100);
        System.out.println(Arrays.toString(data));

        //當limit=0時，效果與split(String regex)一致
        data = line.split("=",0);
        System.out.println(Arrays.toString(data));

        //當limit<0時，表達應拆盡拆
        data = line.split("=",-1);
        System.out.println(Arrays.toString(data));
    }
}
