package com.birdboot.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * 響應对象
 * 該類的每一個實例用於表示HTTP協議規定的響應
 * 一個響應由三部分構成
 * 狀態行，響應頭，響應正文
 */
public class HttpServletResponse {
    private Socket socket;

    //狀態行相關信息
    private int statusCode;//狀態代碼
    private String statusReason;//狀態描述

    //響應頭相關信息

    //響應正文相關信息
    private File contentFile;//正文對應的實體文件

    public HttpServletResponse(Socket socket) {
        this.socket = socket;
    }

    /**
     * 響應方法
     * 該方法會將當前響應對象中的内容按照標準的HTTP響應格式發送给客戶端
     */
    public  void response() throws IOException {
        //1.發送狀態行
        sendStatusLine();
        //2.發送響應頭
        sendHeaders();
        //3.發送響應正文
        sendContent();
    }

    //1.發送狀態行
    public void sendStatusLine() throws IOException {
        println("HTTP/1.1"+" "+statusCode+" "+statusReason);
    }

    //2.發送響應頭
    public void sendHeaders() throws IOException {
        println("Content-Type: text/html");
        println("Content-Length: " + contentFile.length());
        //單獨發送了Enter+換行表達影響頭部分發送完畢了
        println("");
    }

    //3.發送響應正文
    public void sendContent() throws IOException {
        OutputStream out = socket.getOutputStream();
        FileInputStream fis = new FileInputStream(contentFile);
        int len = 0;
        byte[] buf = new byte[1024*10];
        while((len = fis.read(buf))!=-1){
            out.write(buf,0,len);
        }
    }


    /**
     * 向客戶端發送一行字符串
     * @param line
     */
    private void println(String line) throws IOException {
        OutputStream out = socket.getOutputStream();
        byte[] data = line.getBytes(StandardCharsets.ISO_8859_1);
        out.write(data);
        out.write(13);
        out.write(10);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public File getContentFile() {
        return contentFile;
    }

    public void setContentFile(File contentFile) {
        this.contentFile = contentFile;
    }
}
