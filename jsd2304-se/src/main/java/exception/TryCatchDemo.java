package exception;

/**
 * java異常處理機制
 * 語法:
 * try{
 *     可能出現異常的代碼片段(A計畫)
 * }catch(XXXException e){
 *     try中出現XXXException後的處理代碼(B計畫)
 * }
 */
public class TryCatchDemo {
    public static void main(String[] args) {
        System.out.println("程序開始了");
        try {
//            String line = null;
//            String line = "";
            String line = "a";
         /*
            當JVM執行程序是出現了某個異常時,就會實例化對應的異常實例並將其
            抛出.此時虛擬機會檢查抱錯的這句代碼是否有被異常處理機制處理,如果
            没有則會繼續將該異常抛出到當前方法之外(比如這里會抛出到main方法之外)
            如果異常最終被抛出到main方法意味著當前程序就结束了.

            控制台上的異常信息:                     異常的類型(通過它判斷程序出現了什么問題)
            Exception in thread "main" java.lang.NullPointerException
            at 某包.某包....(某類.java:xx)    <-第一行是實際報錯的代碼(後期開發不是我們自己寫的代碼)
            at 某包.某包....(某類.java:xx)    <-第二行如果存在,說明是它調用的上面第一行的方法,導致該方法報錯,並且抛给了這裡
            at 某包.某包....(某類.java:xx)
            at 某包.某包....(某類.java:xx)
            at 某包.某包....(某類.java:xx)
            at 某包.某包....(某類.java:xx)
            at 某包.某包....(某類.java:xx)
            at 某包.某包....(某類.java:xx)
	        at exception.TryCatchDemo.main(TryCatchDemo.java:17)  <-當看到我們寫的包和類時,就應當從這裡開始排錯,在哪個方法的第幾行出現的錯誤
         */

            System.out.println(line.length());
            System.out.println(line.charAt(0));
            System.out.println(Integer.parseInt(line)); //try中代碼不出現異常時,不執行
//        }catch(NullPointerException e){
//            System.out.println("出現了空指針,在這裡解决了!");
//        //針對try中不同異常可以分别進行處理
//        }catch(StringIndexOutOfBoundsException e){
//            System.out.println("出現了字符串下標越界,在這裡解决了!");
//        }
            //JDK7之後推出了一個新特性,合併catch,當多個異常具有相同B計畫時可以使用一個catch
        }catch(NullPointerException|StringIndexOutOfBoundsException e){
            System.out.println("出現了空指針或下標越界的統一處理手段!");
            //捕獲一個超類型異常時,try中出現它的子類型異常都可以被其捕获
            //多個catch存在繼承關係時,子類型在上先捕獲.超類在下後捕獲.
            //上面的catch捕獲異常後,下面的catch就不執行了
        }catch(Exception e){
            System.out.println("反正就是出了個錯");
        }
        System.out.println("程序结束了");
    }
}
