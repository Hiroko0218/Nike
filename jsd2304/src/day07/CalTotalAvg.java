package day07;

import java.util.Scanner;

/**
 * 需求:
 *   <<主持人大赛>>有N名评委给选手打分，要求分数范围为0到100之间的浮点数
 *   选手的最终得分为: 去掉最高分和最低分后的N-2名评委的平均分
 * 训练目标: 方法的设计
 */
public class CalTotalAvg {
    public static void main(String[] args) {
        double[] scores = inputData(6); //1)录入评委的评分
        double avg = calAvg(scores); //2)计算平均分
        System.out.println("平均分为:"+avg);
    }

    /** 录入N位评委的评分 */
    public static double[] inputData(int count){
        Scanner scan = new Scanner(System.in);
        double[] scores = new double[count]; //评分数组
        for(int i=0;i<scores.length;i++){
            System.out.println("请输入第"+(i+1)+"位评委的分数:");
            scores[i] = scan.nextDouble();
        }
        return scores;
    }

    /** 计算平均分 */
    public static double calAvg(double[] scores){
        double total = calTotal(scores); //获取去掉最高分和最低分之后的总分
        double avg = total/(scores.length-2); //平均分
        return avg;
    }

    /** 计算去掉最高分和最低后的总分 */
    public static double calTotal(double[] scores){
        double total = 0.0; //总分
        double max = scores[0]; //假设第1个元素为最高分
        double min = scores[0]; //假设第1个元素为最低分
        for(int i=0;i<scores.length;i++){
            if(scores[i]>max){ //找最高分
                max = scores[i];
            }
            if(scores[i]<min){ //找最低分
                min = scores[i];
            }
            total += scores[i];
        }
        return total-max-min; //返回去掉最高分和最低分之后的总分
    }
}












