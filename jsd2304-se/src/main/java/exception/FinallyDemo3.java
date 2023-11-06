package exception;

/**
 * finally相關面試問題
 *
 * 1:請分别說明 final,finally,finalize?
 *   final: 不可修改
 *   finally: 只要程序執行到 try當中,無論 try中的代碼是否出現異常,最终都要執行 finally中的代碼
 *   finalize: 是Object當中定義的一個方法,意味著java中所有的類都有該方法.
 *            該方法是一個對象生命周期中的最後一個被調用的方法,該方法被 GC調用
 *            一旦調用後該對象會被GC釋放.
 *            API手册上說: Object中該方法没有任何實質操作,如果一個類重寫了該
 *            方法需要注意,不應當有耗時的操作.
 *
 * 2:下面代碼進行展示
 */
public class FinallyDemo3 {
    public static void main(String[] args) {
        System.out.println(test("0")+","+test(null)+","+test(""));//輸出3,3,3
    }
    public static int test(String str){
        try {
            return str.charAt(0)-'0';
        } catch (NullPointerException e) {
            return 1;
        } catch (Exception e){
            return 2;
        }finally {
            return  3; //finally中通常不會寫return
        }
    }
}
