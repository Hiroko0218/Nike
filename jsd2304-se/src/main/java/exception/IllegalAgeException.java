package exception;

/**
 * illegal:非法的
 *
 * 自定義異常
 * 自定異常通常用於表達項目中那些不滿足業務的錯誤.
 *
 * 自定義異常的創建應當遵循以下原則:
 * 1:類名要見名知意
 * 2:要繼承自Exception(直接或間接均可)
 * 3:提供超類異常定義的所有種類構造器
 */
public class IllegalAgeException extends Exception{
    public IllegalAgeException() {
    }
    public IllegalAgeException(String massage){
        super(massage);
    }

    public IllegalAgeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalAgeException(Throwable cause) {
        super(cause);
    }

    public IllegalAgeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
