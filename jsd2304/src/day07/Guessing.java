package day07;
import java.util.Scanner;
import java.util.Random;

/**
 * 需求:猜数字小游戏
 * 训练目标: while(true)自造死循环+break
 */
public class Guessing {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();
        int num = rand.nextInt(1000)+1; //1到1000
        System.out.println(num); //作弊

        while(true){ //自造死循环
            System.out.println("猜吧!");
            int guess = scan.nextInt();
            if(guess>num){
                System.out.println("太大了!");
            }else if(guess<num){
                System.out.println("太小了!");
            }else{
                System.out.println("恭喜你猜对了!");
                break; //跳出循环
            }
        }
    }
}










