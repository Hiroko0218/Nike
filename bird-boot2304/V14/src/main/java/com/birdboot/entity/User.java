package com.birdboot.entity;

import java.io.Serializable;

/**
 * entity:实体  通常表示实际保存在磁盘上的对象数据
 *
 * User每一个实例用于表示一个注册用户信息
 *
 * JAVA BEAN定义规范
 * 1:属性私有话
 * 2:提供属性对应的getter,setter
 * 3:要有无参构造器
 */
public class User implements Serializable {
    private String username;
    private String password;
    private String nickname;
    private int age;

    public User(){

    }

    public User(String username, String password, String nickname, int age) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", age=" + age +
                '}';
    }
}
