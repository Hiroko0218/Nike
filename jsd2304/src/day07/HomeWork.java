package day07;

import java.util.Random;
import java.util.Scanner;

public class HomeWork {
    public static void main(String[] args) {
        double num = getNum();
        System.out.println((int) (num * 100));

        int b =plus(5,6);
        System.out.println(b);//11---模擬對返回值的後續操作

        int m =5;
        int n =6;
        int a =plus(m,n);//傳的是m,n裡面的值
        System.out.println(a); //11--模擬對返回值的後續操作

        System.out.println("請輸入姓名:");
        Scanner d = new Scanner(System.in);
        String name = d.next();
        System.out.println("請輸入年紀:");
        Scanner e = new Scanner(System.in);
        int age = e.nextInt();
        sayHello(name,age);

        int[]c = generateArray(5,100); //模擬第一個人的訪問
        System.out.println("数组的长度为:"+c.length); //5---模擬隊返回值的後續操作
        for(int i=0;i<c.length;i++){ //---模擬隊返回值的後續操作
            System.out.println(c[i]);
        }

    }

    public static double getNum() {
        //return; //編譯錯誤，return後必須跟一個數據
        //return "abc"; //編譯錯誤，return後數據的類型必須與返回值類型匹配
        return 8.88; //1)結束方法的執行 2)返回結束給調用方
    }

    public static int plus(int num1, int num2) {
//        int num = num1+num2;
//        return num;//返回的是num裡面的數
        return num1 + num2;//返回的是num1和num2的和
    }

    public static void sayHello(String name,int age){//----形參
        if (age>=50){ //在某種特定條件下，提前結束方法
            return; //結束方法的運行
        }
        System.out.println("大家好，我叫"+name+"，我今年"+age+"歲了!");
    }

    public static int[] generateArray(int len,int max){
        Random rand = new Random();
        int[] arr = new int[len];
        for (int i =0; i<arr.length;i++){
            arr[i] =rand.nextInt(max+1);//包括max
        }
        return arr;
    }
}
