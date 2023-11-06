package day05;
import java.util.Arrays;
//数组的演示
public class ArrayDemo {
    public static void main(String[] args) {
        //5)数组的排序:
        int[] arr = new int[10];
        for(int i=0;i<arr.length;i++){
            arr[i] = (int)(Math.random()*100);
            System.out.println(arr[i]);
        }

        Arrays.sort(arr); //对arr数组做升序排列

        System.out.println("排序后:");
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }

        System.out.println("倒着输出:");
        for(int i=arr.length-1;i>=0;i--){ //数据还是升序的，只是倒着展示
            System.out.println(arr[i]);
        }
        System.out.println("第1个元素为:"+arr[0]); //小

        /*
        //1)数组的定义:
        //声明整型数组a，包含4个元素，每个元素都是int型，默认值为0
        int[] a = new int[4];
        //声明浮点型数组b，包含8个元素，每个元素都是double型，默认值为0.0
        double[] b = new double[8];
        //声明布尔型数组d，包含20个元素，每个元素都是boolean型，默认值为false
        boolean[] d = new boolean[20];

        //2)数组的初始化:初始化的是数组中的元素
        int[] arr1 = new int[3]; //0,0,0
        int[] arr2 = {2,5,8}; //2,5,8
        int[] arr3 = new int[]{2,5,8}; //2,5,8
        int[] arr4;
        //arr4 = {2,5,8}; //编译错误，此方式只能声明同时初始化
        arr4 = new int[]{2,5,8}; //正确

        //3)数组的访问:访问是数组中的元素
        int[] arr = new int[3];
        System.out.println("数组的长度:"+arr.length); //3
        System.out.println(arr[0]); //0，输出第1个元素的值
        arr[0] = 100; //给第1个元素赋值为100
        arr[1] = 200; //给第2个元素赋值为200
        arr[2] = 300; //给第3个元素赋值为300
        //arr[3] = 400; //运行时会发生数组下标越界异常
        System.out.println(arr[arr.length-1]); //300，输出最后一个元素的值
         */

        /*
        //4)数组的遍历/迭代:从头到尾挨个走一遍
        int[] arr = new int[10];
        for(int i=0;i<arr.length;i++){ //遍历arr数组
            arr[i] = (int)(Math.random()*100); //给每个元素赋值为0到99的随机数
            System.out.println(arr[i]); //输出每个元素的值
        }
        */

    }
}

















