package com.birdboot.http;

import javax.activation.MailcapCommandMap;
import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 響應对象
 * 該類的每一個實例用於表示HTTP協議規定的響應
 * 一個響應由三部分構成
 * 狀態行，響應頭，響應正文
 */
public class HttpServletResponse {
    private static MimetypesFileTypeMap mftm = new MimetypesFileTypeMap();

    private Socket socket;

    //狀態行相關信息
    private int statusCode;//狀態代碼
    private String statusReason;//狀態描述

    //響應頭相關信息
    //key:響應頭的名字  value:響應頭對應的值
    private Map<String,String> headers = new HashMap<>();

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
        //遍歷headers將所有待發送的響應頭發送给客戶端
        Set<Map.Entry<String,String>> entrySet = headers.entrySet();
        for(Map.Entry<String,String> e :entrySet){
            /*
                headers
                key                 value
                Content-Type        text/html           Entry
                Content-Length      245                 Entry
                Server              BirdWebServer       Entry
             */
            String key = e.getKey();//響應頭名字
            String value = e.getValue();//響應頭對應的值
            println(key + ": " + value);
        }
        //單獨發送了Enter+換行表達影響頭部分發送完畢了
        println("");
    }

    //3.發送響應正文
    public void sendContent() throws IOException {
        if(contentFile!=null) {//包含正文時才發送正文文件
            OutputStream out = socket.getOutputStream();
            FileInputStream fis = new FileInputStream(contentFile);
            int len = 0;
            byte[] buf = new byte[1024 * 10];
            while ((len = fis.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
        }
    }

    /**
     * 要求客戶端重定向到指定位置
     * @param uri
     */
    public void sendRedirect(String uri){
        //1.設定狀態代碼302
        setStatusCode(302);
        setStatusReason("Moved Temporarily");
        //2.添加響應頭Location
        addHeader("Location",uri);
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

    /**
     * 添加正文文件
     * 在添加文件的同時會自動根據該文件添加對應響應頭:Content-Type和 Content-Length
     * @param contentFile
     */
    public void setContentFile(File contentFile) {
        this.contentFile = contentFile;
        addHeader("Content-Type",mftm.getContentType(contentFile));
        addHeader("Content-Length",contentFile.length()+"");
    }

    /**
     * 添加一個響應頭
     * @param name
     * @param value
     */
    public void addHeader(String name, String value){
        headers.put(name,value);
    }
}
