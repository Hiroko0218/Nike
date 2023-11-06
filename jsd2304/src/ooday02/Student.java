package ooday02;
/** 学生类-----派生类 */
public class Student extends Person {
    String className;
    String stuId;
    Student(){
    }
    Student(String name,int age,String address,String className,String stuId){
        super(name,age,address); //传递的是name/age/address的值
        this.className = className;
        this.stuId = stuId;
    }

    void study(){
        System.out.println(name+"正在学习...");
    }

    void sayHi(){
        System.out.println("大家好，我叫"+name+"，今年"+age+"岁了，家住"+address+"，所在班级为:"+className+"，学号为:"+stuId);
    }
}













