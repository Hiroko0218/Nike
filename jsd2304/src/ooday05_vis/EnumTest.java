package ooday05_vis;
//复杂版枚举的演示
public class EnumTest {
    public static void main(String[] args) {
        Seasons s = Seasons.WINTER; //获取一个季节对象
        System.out.println(s.getSeasonName()+","+s.getSeasonDesc());

        Seasons[] seasons = Seasons.values(); //获取所有季节对象
        for(int i=0;i<seasons.length;i++){
            System.out.println(seasons[i]);
            System.out.println(seasons[i].getSeasonName());
            System.out.println(seasons[i].getSeasonDesc());
        }
    }
}













