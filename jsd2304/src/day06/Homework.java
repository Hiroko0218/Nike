package day06;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Homework {
    public static void main(String[] args) {

//        Scanner c = new Scanner(System.in);
//        int score = 0;
//        int s = 0;
//        for (int i = 0; i < 10; i++) {
//            int a = (int) (Math.random() * 100);
//            int b = (int) (Math.random() * 100);
//            System.out.println("(" + (i + 1) + ") " + a + "+" + b + "=?");
//            int answer = c.nextInt();
//            if (answer == a + b) {
//                System.out.println("答對了!");
//                score = score + 10;
//                s++;
//                continue;
//            }
//            System.out.println("答錯了!");
//        }
//        System.out.println("共答對" + s + "題，" + "總得" + score + "分");

        for (int i = 1;i<=9;i++){
            for (int j = 1; j<=i;j++){
                System.out.print(i+"*"+j+"="+(i*j)+" ");
            }
            System.out.println();
        }

        boolean[] d = new boolean[3];
        d[0] = false;
        d[1] = true;
        d[2] = false;
        System.out.println(d[2]);

        String[] g = new String[5];
        System.out.println(g.length);

        double[] h = {100, 40.5, 60.9, 70.4, 50.2, 30.7, 90.1, 80.8};
        System.out.println(h[5]);

        for (int i = 0; i < h.length; i++) {
            System.out.println(h[i]);
        }


        int [] arr= new int[10];
        int max =arr[0] ;
        for (int i = 1;i<arr.length;i++){
            arr[i] =(int)(Math.random()*100);
            System.out.print(arr[i]+" ");
            if (arr[i]>max){
                max=arr[i];
            }
        }
        System.out.println();
        System.out.println("最大值: "+max);

        int [] arr1= new int[10];
        for (int i = 0;i<arr1.length;i++){
            arr1[i] =(int)(Math.random()*100);
            System.out.print(arr1[i]+" ");
        }
        Arrays.sort(arr1);
        System.out.println();
        System.out.println("排序後:");
        for(int i = 0; i<arr1.length;i++){
            System.out.print(arr1[i]+" ");
        }
        System.out.println();
        System.out.println("倒著輸出:");//數據是升序，只是倒著展示
        for (int i = arr1.length-1 ; i>=0;i--){
            System.out.print(arr1[i]+" ");
        }
        System.out.println();
        System.out.println("第一個數值為: "+arr1[0]);//因為數據還是升序，所以是"小"
    }
}
