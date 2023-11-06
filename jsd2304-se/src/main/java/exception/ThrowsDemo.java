package exception;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 子類重寫超類含有throws聲明異常抛出的方法時,對throws的重寫規則
 */
public class ThrowsDemo {
    public void dosome()throws IOException, AWTException {}
}
class SubClass extends ThrowsDemo{
//    public void dosome()throws IOException, AWTException {}

//    子類可以不再抛出任何異常
//    public void dosome(){}

//    子类可以僅抛出超類方法聲明異常的部分異常
//    public void dosome()throws IOException{}

//    子類方法可以拋出超类方法聲明抛出異常的子類型異常
//    public void dosome()throws FileNotFoundException {}

//    不允許抛出額外異常.超類方法沒有聲明的,且與聲明的異常不存在繼承關係的
//    public void dosome()throws SQLException {}

//    不允許抛出超類方法聲明異常的超類型異常
//    public void dosome()throws Exception {}
}
