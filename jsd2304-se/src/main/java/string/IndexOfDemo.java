package string;

public class IndexOfDemo {
    public static void main(String[] args) {
        String line = "thinking in java";

        //檢索當前字符串"in"第一次出現的位置
        int index = line.indexOf("in");
        System.out.println(index);//2

        //從下標3處開始檢索第一次出現"in"的位置
        index = line.indexOf("in",3);
        System.out.println(index);//5

        //檢索當前字符串中最後一次出现"in"的位置
        index = line.lastIndexOf("in");
        System.out.println(index);//9

        //如果當前字符串檢索中不含有指定内容則返回值為-1
        index = line.indexOf("IN");
        System.out.println(index);//-1
    }
}
