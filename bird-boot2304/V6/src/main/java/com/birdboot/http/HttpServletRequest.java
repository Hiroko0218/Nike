package com.birdboot.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 請求對象
 * 該類的每一個實例表示 HTTP協議要求的瀏覽器發送给服務端的一個請求
 * 每個請求由三個部分購成:
 * 請求行，消息頭，消息正文
 *
 */
public class HttpServletRequest {
    private Socket socket;
    //請求行相關訊息
    private String method;//請求方式
    private String uri;//抽象路徑
    private String protocol;//協議版本
    //讀取消息頭
    Map<String,String> headers = new HashMap<>();

    public HttpServletRequest (Socket socket) throws IOException {
        this.socket = socket;
        //1.解析請求行
        parseRequestLine();
        //2.解析消息頭
        parseHeaders();
        //3.解析消息頭正文
        parseContent();
    }
    //解析請求行
    private void  parseRequestLine () throws IOException {
        //實現讀取一行字符串的操作(瀏覽器發送過来的内容為請求，其中第一行應當是請求行)
        String line = readLin();
        System.out.println("請求行:"+line);

        String[] data = line.split("\\s");
        method = data[0];
        uri = data[1];
        protocol = data[2];

        System.out.println("method: "+method);
        System.out.println("uri: "+uri);
        System.out.println("protocol: "+protocol);
    }

    //解析消息頭
    private void parseHeaders() throws IOException {
        //讀取消息頭
        while (true){//while循環是因為瀏覽器發送多少個消息頭不確定
            String line = readLin();
            if (line.isEmpty()) {//如果readLine返回空字符串，說明單獨讀取了Enter+換行
                break;//因为單獨的Enter+換行表示消息頭部分發送完畢
            }
            System.out.println("消息頭: "+line);
            //將消息頭按照冒號空格拆分為消息頭名字和值並以key,value形式保存到headers中
            String[] data = line.split(":\\s");
            headers.put(data[0],data[1]);
        }
        System.out.println("headers: "+headers);
    }

    //解析消息正文
    private void parseContent(){

    }

    /**
     * 讀取客戶端發送過来的一行字符串
     * @return
     */
    private String readLin() throws IOException {
        //實現讀取一行字符串的操作(瀏覽器發送過来的内容為請求，其中第一行應當是請求行)
        InputStream in = socket.getInputStream();

        char pre = 'a', cur = 'a';//pre表示上次讀取的字符，cur表示本次讀取的字符
        StringBuilder builder = new StringBuilder();//紀錄以讀取一行字符串的内容
        int d;//每次讀取的字節
        while ((d = in.read()) != -1) {
            cur = (char) d;//本次讀取的字符
            if (pre == 13 && cur == 10) {//是否已經连連續取到了Enter+換行
                break;
            }
            builder.append(cur);//將本次讀取的字符拼接
            pre = cur;//再下次讀取前，將本次讀取的字符記為上次讀取的字符
        }
        //trim的目的是將最後讀取到的Enter去除
        return builder.toString().trim();
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getProtocol() {
        return protocol;
    }

    /**
     * 根據消息頭的名字獲取對應的值
     * @param name
     * @return
     */
    public String getHeaders(String name) {
        return headers.get(name);
    }
}
