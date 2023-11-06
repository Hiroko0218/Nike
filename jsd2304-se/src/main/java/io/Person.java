package io;

import java.io.Serializable;

/**
 * 使用該類的實例測試對象流的對象讀寫操作
 */
public class Person implements Serializable {
    private String name;
    private int age;
    private String gender;
    /*
        transient關鍵字
        被該關鍵字修飾的屬性在進行對象序列化是會被忽略。
        忽略不必要的属性可以達到對象序列化的"瘦身"目的，减少不必要的開銷
        持久化時可以降低磁盤空間的開銷網絡傳輸時可以提高傳輸速度
     */
    private transient String[] otherInfo;

    public Person(String name, int age, String gender, String[] otherInfo) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String[] getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String[] otherInfo) {
        this.otherInfo = otherInfo;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
