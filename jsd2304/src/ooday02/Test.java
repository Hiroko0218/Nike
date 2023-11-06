package ooday02;
/** 测试类 */
public class Test {
    public static void main(String[] args) {
        Dog dog1 = new Dog("小黑",2,"黑");
        dog1.eat();
        dog1.drink();
        dog1.lookHome();

        Dog dog2 = new Dog("小白",1,"白");
        dog2.eat();
        dog2.drink();
        dog2.lookHome();

        Dog dog3 = new Dog("小强",2,"黑白");
        dog3.eat();
        dog3.drink();
        dog3.lookHome();

        Chick chick = new Chick("花花",1,"棕");
        chick.eat();
        chick.drink();
        chick.layEggs();

        Fish fish = new Fish("小金",2,"金");
        fish.eat();
        fish.drink();
    }
}




















