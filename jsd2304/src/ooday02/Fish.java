package ooday02;
/** 鱼 */
public class Fish extends Animal {
    Fish(String name,int age,String color){
        super(name,age,color);
    }

    void eat(){
        System.out.println(color+"色的"+age+"岁的"+name+"正在吃小虾...");
    }
}



















