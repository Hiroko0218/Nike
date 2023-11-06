package day07;

/**
 * 需求:---------常见面试题
 *   找到2到100之间的所有素数(质数)
 *   素数:除了1和它本身外，不能被其它任何自然数整除的数----只能被1和它本身整除
 * 训练目标: 通过boolean的flag打标记(3步)
 */
public class PrimeNumber {
    public static void main(String[] args) {
        for(int num=2;num<=100;num++){
            boolean flag = true; //假设每个num都是素数
            for(int i=2;i<=num/2;i++){
                if(num%i==0){
                    flag = false;
                    break;
                }
            }
            if(flag){
                System.out.print(num+"\t");
            }
        }

        /*
        // 7%2/3/4/5/6，但凡有1个为0的，就能说明它不是素数，只有都不为0的，才是素数
        int num = 7;  //带数(7/8/9/11)
        boolean flag = true; //1)假设num是素数
        for(int i=2;i<=num/2;i++){ //i=2/3/4/5/6
            if(num%i==0){
                flag = false; //2)修改为不是素数
                break;
            }
        }
        if(flag){ //3)判断flag标记
            System.out.println(num+"是素数");
        }else{
            System.out.println(num+"不是素数");
        }
         */

    }
}

















