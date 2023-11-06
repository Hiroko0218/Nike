package V20.src.main.java.com.birdboot.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
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
    private String requestURI;//保存uri中"?"左側的請求部分
    private String queryString;//保存uri中"?"右側的参數部分
    private Map<String,String> parameters = new HashMap<>();//保存每一组參數

    //讀取消息頭
    Map<String,String> headers = new HashMap<>();

    public HttpServletRequest (Socket socket) throws IOException,EmptyRequestException {
        this.socket = socket;
        //1.解析請求行
        parseRequestLine();
        //2.解析消息頭
        parseHeaders();
        //3.解析消息頭正文
        parseContent();
    }
    //解析請求行
    private void  parseRequestLine () throws IOException,EmptyRequestException {
        //實現讀取一行字符串的操作(瀏覽器發送過来的内容為請求，其中第一行應當是請求行)
        String line = readLine();

        if(line.isEmpty()){//若請求行為空字符串，說明本次為空請求
            throw new EmptyRequestException();
        }

        System.out.println("請求行:"+line);

        String[] data = line.split("\\s");
        method = data[0];
        uri = data[1];
        protocol = data[2];

        //進一步解析uri
        parseURI();

        System.out.println("method: "+method);
        System.out.println("uri: "+uri);
        System.out.println("protocol: "+protocol);
    }

    //進一步解析uri
    private void parseURI(){
        String[] data = uri.split("\\?");
        requestURI = data[0];//請求部分

        if(data.length>1){//如果有參數部分
            queryString = data[1];//賦值给参數
            //對参數部分進行拆分，得到每一组参數
            parseParameters(queryString);
        }
        System.out.println("requestURI:"+requestURI);
        System.out.println("queryString:"+queryString);
        System.out.println("parameters:"+parameters);
    }

    //解析參數
    private void parseParameters(String line) {
        //先對参數部分解碼
        try {
            line = URLDecoder.decode(line,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //paraArr:[username=fancq, password=123456, nickname=chuanqi, age=22]
        String[] paraArr = line.split("&");
        //遍歷每一组參數並再次拆分為参數名與参數值
        for (String para : paraArr) {
            //para:"username="
            //paras:[username, fancq]
            String[] paras = para.split("=", 2);
            parameters.put(paras[0], paras[1]);
        }
    }

    //解析消息頭
    private void parseHeaders() throws IOException {
        //讀取消息頭
        while (true){//while循環是因為瀏覽器發送多少個消息頭不確定
            String line = readLine();
            if (line.isEmpty()) {//如果readLine返回空字符串，說明單獨讀取了Enter+換行
                break;//因为單獨的Enter+換行表示消息頭部分發送完畢
            }
            System.out.println("消息頭: "+line);
            //將消息頭按照冒號空格拆分為消息頭名字和值並以key,value形式保存到headers中
            String[] data = line.split(":\\s");
            //改造:將消息頭的名字轉換為全小寫再保存，以便獲取消息頭時忽略大小寫問題
            headers.put(data[0].toLowerCase(),data[1]);
        }
        System.out.println("headers: "+headers);
    }

    //解析消息正文
    private void parseContent(){
        //根據請求方式是否為POST决定是否要解析正文
        if("post".equalsIgnoreCase(method)){
            //根據消息頭Content-Length來確定正文長度
            String contentLength = getHeader("Content-Length");
            if(contentLength!=null) {
                //確定正文長度
                int cl = Integer.parseInt(contentLength);
                System.out.println("正文長度:"+cl);
                //讀取正文數據
                byte[] data = new byte[cl];
                try {
                    InputStream in = socket.getInputStream();
                    in.read(data);//將正文數據全部讀入到data數組中
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //根據Content-Type来分析正文類型並進行對應的解析
                String contentType = getHeader("Content-Type");
                if(contentType!=null){
                    if("application/x-www-form-urlencoded".equals(contentType)){
                        String line = new String(data, StandardCharsets.ISO_8859_1);
                        System.out.println("正文内容:"+line);
                        parseParameters(line);

                    }//讀後可以解析其他類型的正文
//                    else if("xxxx/xxxx".equals(contentType)){}
                }
            }
        }
    }

    /**
     * 讀取客戶端發送過来的一行字符串
     * @return
     */
    private String readLine() throws IOException {
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
    public String getHeader(String name) {
        //改造，獲取消息頭時，也要將名字轉換為全小寫再去headers中作為key提取value
        return headers.get(name.toLowerCase());
    }

    public String getRequestURI() {
        return requestURI;
    }

    public String getQueryString() {
        return queryString;
    }

    public String getParameter(String name) {
        return parameters.get(name);
    }
}

