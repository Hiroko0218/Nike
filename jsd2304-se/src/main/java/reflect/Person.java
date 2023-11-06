package reflect;


import reflect.annotations.AutoRunClass;
import reflect.annotations.AutoRunMethod;

/**
 * 使用當前類測試反射機制
 */
@AutoRunClass
public class Person {
    private String name = "張三";
    private int age = 22;

    public Person(){}

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void sayHello(){
        System.out.println(name+":hello!");
    }
    @AutoRunMethod(20)
    public void sayHi(){
        System.out.println(name+":hi!");
    }
    @AutoRunMethod(6)
    public void sleep(){
        System.out.println(name+"在睡覺");
    }
    public void watchTV(){
        System.out.println(name+"在看電視");
    }
    @AutoRunMethod
    public void playGame(){
        System.out.println(name+"玩遊戲");
    }
    public void say(String info){
        System.out.println(name+":"+info);
    }
    public void say(String info,int count){
        for (int i = 0; i < count; i++) {
            System.out.println(name+":"+info);
        }
    }
    public void swing(){
        System.out.println(name+":在游泳");
    }
    private void hehe(){
        System.out.println("我是Person的私有方法!!");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
