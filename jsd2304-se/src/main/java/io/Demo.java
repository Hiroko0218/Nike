package io;

public class Demo {
    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        System.out.println(now);

        long y = now /1000/60/60/24/365;
        System.out.println(y+"年");

        long max = Long.MAX_VALUE;
        max = max/1000/60/60/24/365;
        System.out.println("公元: "+(1970+max));
    }
}
