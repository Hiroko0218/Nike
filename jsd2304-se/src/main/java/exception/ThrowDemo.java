package exception;

/**
 * throw關鍵字
 * throw可以允許我們主動在方法中對外抛出一個異常
 * 通常以下情况我們会這樣做:
 * 1:方法中出現了一個異常,但是該異常不應當在當前方法中被處理
 * 2:方法運行過程中出現了一個滿足語法但是不滿足業務的情况(本案例演示)
 *
 * 語法:
 * throw 異常實例;
 */
public class ThrowDemo {
    public static void main(String[] args)  {
        Person p = new Person();
        /*
            當我們調用一個含有throws聲明異常抛出的方法時,編譯器要求我們
            必須處理該異常,處理方式有兩種:
            1: 使用try-catch主動捕獲並處理該異常
            2: 在當前方法上繼續使用throws聲明將該異常抛出
         */
        try {
            p.setAge(1000);//滿足語法,但是不滿足業務場景
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("此人年齡"+p.getAge()+"歲");
    }
}
