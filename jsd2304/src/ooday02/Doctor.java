package ooday02;
/** 医生类-----派生类 */
public class Doctor extends Person {
    String title;
    Doctor(){
    }
    Doctor(String name,int age,String address,String title){
        super(name,age,address);
        this.title = title;
    }
    void cut(){
        System.out.println(name+"正在做手术...");
    }
}















