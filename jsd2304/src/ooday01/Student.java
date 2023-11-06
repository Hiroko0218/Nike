package ooday01;
/** 学生类 */
public class Student {
    //成员变量---描述对象的属性
    String name;
    int age;
    String className;
    String stuId;
    //构造方法---给成员变量赋初始值
    Student(){
    }
    Student(String name,int age,String className,String stuId){
        this.name = name;
        this.age = age;
        this.className = className;
        this.stuId = stuId;
    }

    //方法------描述对象的行为
    void study(){
        System.out.println(this.name+"在学习...");
    }

    void sayHi(){
        System.out.println("大家好，我叫"+this.name+"，今年"+this.age+"岁了，所在班级为:"+this.className+"，学号为:"+this.stuId);
    }

    void playWith(String anotherName){
        System.out.println(this.name+"正在和"+anotherName+"一起玩...");
    }
}















