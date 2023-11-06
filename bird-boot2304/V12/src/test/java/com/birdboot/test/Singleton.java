package com.birdboot.test;
/**
 * 單例模式
 * 單例模式是java23種設計模式之一
 * 使用該模式設計的類全局只有一個實例
 *
 * 過程:
 * 1:私有化構造器
 * 2:定義靜態的私有的當前類的屬性並實例化
 * 3:定義公開的靜態的獲取當前類實例的方法，並在方法中返回步驟2定義的私有屬性
 */
public class Singleton {
    private static Singleton instance = new Singleton();

    private Singleton(){}

    public static Singleton getInstance(){
        return instance;
    }
}
