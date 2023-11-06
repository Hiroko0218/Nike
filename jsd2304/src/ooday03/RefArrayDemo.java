package ooday03;
//引用类型数组的演示
public class RefArrayDemo {
    public static void main(String[] args) {
        Dog[] dogs = new Dog[3];
        dogs[0] = new Dog("小黑",2,"黑");
        dogs[1] = new Dog("小白",1,"白");
        dogs[2] = new Dog("小灰",3,"灰");
        System.out.println(dogs[0].name); //输出第1只狗狗的名字
        dogs[1].age = 4; //修改第2只狗狗的年龄为4岁
        dogs[2].swim(); //第3只狗狗在游泳
        for(int i=0;i<dogs.length;i++){ //遍历dogs数组
            System.out.println(dogs[i].name); //输出每只狗狗的名字
            dogs[i].eat(); //每只狗狗吃饭
        }

        Chick[] chicks = new Chick[2];
        chicks[0] = new Chick("小花",1,"花");
        chicks[1] = new Chick("大花",2,"白");
        for(int i=0;i<chicks.length;i++){
            System.out.println(chicks[i].name);
            chicks[i].layEggs();
        }

        Fish[] fish = new Fish[4];
        fish[0] = new Fish("小金",2,"金");
        fish[1] = new Fish("大金",4,"白");
        fish[2] = new Fish("小银",1,"银");
        fish[3] = new Fish("大银",2,"银");
        for(int i=0;i<fish.length;i++){
            System.out.println(fish[i].color);
            fish[i].drink();
        }

        /*
        //声明Dog型数组dogs，包含3个元素，每个元素都是Dog类型，默认值为null
        Dog[] dogs = new Dog[3];
        //声明Chick型数组chicks，包含3个元素，每个元素都是Chick类型，默认值为null
        Chick[] chicks = new Chick[3];
        //声明Fish型数组fish，包含2个元素，每个元素都是Fish类型，默认值为null
        Fish[] fish = new Fish[2];
         */
    }
}














