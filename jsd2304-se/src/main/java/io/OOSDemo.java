package io;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 對象流
 * java.io.ObjectOutputStream 和 ObjectInputStream
 * 對象流是一對高级流,在流連接中的作用是進行對象的序列化與反序列化
 *
 * 對象序列化:將一個java對象轉換為一组可以被保存或傳輸的字節的過程
 */
public class OOSDemo {
    public static void main(String[] args) throws IOException {
        //將一個對象寫入文件person.obj中保存
        String name ="王克晶";
        int age = 18;
        String gender = "女";
        String[] otherInfo = {"嗓門大","黑","技術好","賢妻良母","廊坊佳木斯人"};
        Person p = new Person(name,age,gender,otherInfo);
        System.out.println(p);

        FileOutputStream fos = new FileOutputStream("person.obj");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        /*
            對象輸出流提供的方法:
            void writeObject(Object obj)
            可以將给定對象進行序列化,前提,該對象所屬的類必需實現接口:
            java.io.Serializable
            否則序列化時會抛出異常:java.io.NotSerializableException
         */
        oos.writeObject(p);
        System.out.println("寫出完畢!");
        oos.close();
    }
}
