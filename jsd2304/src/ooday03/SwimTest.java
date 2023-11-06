package ooday03;
/** 游泳接口的测试 */
public class SwimTest {
    public static void main(String[] args) {
        Dog dog = new Dog("小黑",2,"黑");
        dog.eat();       //Dog类重写之后的
        dog.swim();      //Dog类重写之后的
        dog.drink();     //复用Animal类的
        dog.lookHome();  //Dog类所特有的

        Chick chick = new Chick("小白",1,"白");
        chick.eat();      //Chick类重写之后的
        chick.layEggs();  //Chick类所特有的
        chick.drink();    //复用Animal类的

        Fish fish = new Fish("小金",1,"金");
        fish.eat();    //Fish类重写之后的
        fish.swim();   //Fish类重写之后的
        fish.drink();  //复用Animal类的
    }
}


















