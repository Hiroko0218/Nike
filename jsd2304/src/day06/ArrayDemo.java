package day06;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayDemo {
    public static void main(String[] args) {
//        int[] e = new int[5];
//        boolean[] d = new boolean[5];
//        String[] g = new String[5];
//
//        double su = 0;
//        double[] h = {100, 40, 60, 70, 50, 30, 90, 80};
//        for (int i = 0; i < h.length; i++) {
//            su = su + h[i];
//        }
//        System.out.println(su);
//
//        int[] v = new int[10];
//        for (int i = 0; i < v.length; i++) {
//            int m = (int) (Math.random() * 100);
//            System.out.println(m);
//        }
//
//
//        double[] c = new double[5];
//        c[0] = 10;
//        c[1] = 80;
//        c[2] = 40;
//        c[3] = 30;
//        c[4] = 20;
//        for (int i = 0; i < c.length; i++) {
//            System.out.print((int) (c[i]) + " ");
//        }

        // 數組的複製-第一種(靈活性較好)
        int[] a = {10, 20, 30, 40, 50};
        int[] b = new int[5]; //0,0,0,0,0

        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
        }

        // a: 原數組
        // 1: 原數組的起始下標
        // b: 目標數組
        // 0: 目標數組的起始下標
        // 4: 要複製的元素個數
        System.arraycopy(a, 2, b, 1, 3);//靈活性好
        System.out.println();
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
        }
        System.out.println();


        // 數組的複製-第二種(貼上只能從頭開始)
        int[] c = {10, 20, 30, 40, 50};

        // c: 原數組
        // d,e,f: 目標數組
        // newLength: 目標數組長度
        // -- 若目標數組長度 > 原數組長度，則為末尾補默認值0
        // -- 若目標數組長度 < 原數組長度，則將末尾截掉

        int[] d = Arrays.copyOf(c, 4);//末尾截掉
        int[] e = Arrays.copyOf(c, 5);
        int[] f = Arrays.copyOf(c, 6);//末尾補默認值0
        int[] g = Arrays.copyOf(c, c.length + 1);//擴容
        //數組擴容(創建一個更大的新數組,並將數據複製進去了)

        for (int i = 0; i < d.length; i++) {
            System.out.print(d[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < e.length; i++) {
            System.out.print(e[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < f.length; i++) {
            System.out.print(f[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < f.length; i++) {
            System.out.print(g[i] + " ");
        }
        System.out.println();

        int[] arr = new int[10];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
            System.out.print(arr[i] + " ");
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println();
        System.out.println("最大值: " + max);
        arr = Arrays.copyOf(arr, arr.length + 1);//擴容
        arr[arr.length - 1] = max;//將最大值賦值至最後一個元素
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
