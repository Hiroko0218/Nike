package exception;

/**
 * 使用當前類測試異常的抛出
 */
public class Person {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws IllegalAgeException {
        if (age<0||age>100){
            /*
                java將異常分為了兩類:
                1:檢查異常
                2:非檢查異常
                非檢查異常: RuntimeException及其子類異常

                常見的:
                NullPointerException,ArrayIndexOutOfBoundsException,
                ClassCastException,NumberFormatException

                RuntimeException 翻譯:運行時異常,它表示的屬於因為程序邏輯漏洞
                (BUG)引起的異常,這些都是需要修改邏輯就可以完全避免的異常.這類
                異常就不應當用異常處理機制指定B計畫了.

                因此編譯器遇到可能抛出RuntimeException的情况是則不提示必須
                添加異常處理機制.
             */
//            throw new RuntimeException("年齡不合法");
            /*
                在java中除了RuntimeException及其子類異常以外,使用throw
                主動對外抛出一個異常時就應當在當前方法上使用throws聲明該
                異常的抛出来通知調用者處理該異常
             */
//            throw new Exception("年龄不合法");
            throw new IllegalAgeException("年齡超出範圍"+age);
        }
        this.age = age;
    }
}
