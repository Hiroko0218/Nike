package day04;
//for循环的演示
public class ForDemo {
    public static void main(String[] args) {
        //for中的循环变量num的作用域，仅在当前for中-----特殊记忆
        for(int num=1;num<=9;num++){
            System.out.println(num+"*9="+num*9);
        }
        //System.out.println(num); //编译错误，超出num作用域了

        for(int num=1;num<=9;num+=2){
            System.out.println(num+"*9="+num*9);
        }

        for(int times=0;times<5;times++){
            System.out.println("行动是成功的阶梯");
        }
        System.out.println("继续执行...");
        /*
          执行过程:
            times=0  true  输出
            times=1  true  输出
            times=2  true  输出
            times=3  true  输出
            times=4  true  输出
            times=5  false for循环结束
            输出继续执行...
         */
    }
}
















