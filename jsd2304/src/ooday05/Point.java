package ooday05;
/*
  标准JavaBean的规范:
  1.成员变量私有，同时提供对应的公开的getter/setter
  2.包含公开的无参构造方法

  设计getter/setter的原因:
  1.很多框架的配置操作都是基于getter/setter，没有它就获取不到数据，
    可以将设计getter/setter理解为一种行为标准
  2.可以更好的保证数据的合法性(因为方法中可以做条件控制)
  3.getter/setter可以选择性存在(只有getter(只读)，或者只有setter(只写))
 */
public class Point {
    private int x;
    private int y;

    public int getX(){ //getter获取
        return x;
    }
    public void setX(int x){ //setter设置
        this.x = x;
    }

    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y = y;
    }
}




























