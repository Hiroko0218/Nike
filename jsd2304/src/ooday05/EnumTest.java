package ooday05;
//枚举的测试类
public class EnumTest {
    public static void main(String[] args) {
        Seasons[] seasons = Seasons.values(); //获取所有枚举对象
        for(int i=0;i<seasons.length;i++){
            System.out.println(seasons[i]);
        }

        /*
        Seasons s = Seasons.WINTER; //获取WINTER对象
        switch(s){
            case SPRING:
                System.out.println("春天到了...");
                break;
            case SUMMER:  //练习----------------------5:35继续
                System.out.println("夏天到了...");
                break;
            case AUTUMN:
                System.out.println("秋天到了...");
                break;
            case WINTER:
                System.out.println("冬天到了...");
                break;
        }
         */
    }
}














