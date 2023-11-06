package day05;

import java.util.Scanner;

public class HomeWork {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            System.out.println("跑一圈了");
        }
        System.out.println("已跑3圈");


        for (int i = 0; i < 5; i++) {
            System.out.println("學習JAVA");
        }
        System.out.println("找到一個好工作");


        for (int i = 6; i > 0; i--) {
            System.out.println("剩下" + i + "個包子");
        }
        System.out.println("吃飽了");


        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum = sum + i;
        }
        System.out.println("1~100的總和為: " + sum);


        int a = 0;
        for (int i = 0; i <= 100; i++) {
            if (i % 3 == 0) {
                a++;
            }
        }
        System.out.println("1~100之間，能被3整除的數有" + a + "個");


        for (int i = 0; i <= 15; i++) {
            if (i % 2 == 0) {
                System.out.println("偶數為: " + i);
            } else {
                System.out.println("奇數為: " + i);
            }
        }
        System.out.println();

        Scanner f = new Scanner(System.in);
        System.out.println("請輸入成績:");
        int scoress = f.nextInt();
        if (scoress >= 90 && scoress <= 100) {
            System.out.println("判斷級別: A");
        } else if (scoress >= 80 && scoress < 90) {
            System.out.println("判斷級別: B");
        } else if (scoress >= 70 && scoress < 80) {
            System.out.println("判斷級別: C");
        } else if (scoress < 60 && scoress >= 0) {
            System.out.println("判斷級別: D");
        } else {
            System.out.println("輸入錯誤!");
        }
        System.out.println();

        Scanner e = new Scanner(System.in);
        System.out.println("請輸入成績:");
        int scores = e.nextInt();
        if (scores >= 60 && scores < 100) {
            System.out.println("恭喜及格!");
        } else if (scores < 60) {
            System.out.println("殘念!");
        } else {
            System.out.println("輸入錯誤!!");
        }
        System.out.println();

        Scanner s = new Scanner(System.in);
        int score = 0;
        for (int i = 0; i <= 5; i++) {
            int b = (int) (Math.random() * 100);
            int c = (int) (Math.random() * 100);
            System.out.println("(" + (i + 1) + ")" + b + "+" + c + "=?");
            int answer = s.nextInt();
            if (answer == b + c) {
                System.out.println("答對了");
                score++;
                continue;
            }
            System.out.println("答錯了");
        }
        System.out.println("總共答對" + score + "題");
    }
}
