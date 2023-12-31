package com.tedu.springboot2304.entity;

import java.io.Serializable;

/**
 * entity:實體  通常表示實際保存在磁盤上的對象數據
 *
 * User每一個實例用於表示一個註冊用戶信息
 *
 * JAVA BEAN定義規範
 * 1:屬性私有化
 * 2:提供屬性對應的 getter,setter
 * 3:要有無參構造器
 */
public class User implements Serializable {
    private String username;
    private String password;
    private String nickname;
    private int age;

    public User() {
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
