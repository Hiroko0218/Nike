package com.birdboot.core;

import com.birdboot.controller.UserController;
import com.birdboot.http.HttpServletRequest;
import com.birdboot.http.HttpServletResponse;

import java.io.File;
import java.net.URISyntaxException;

/**
 * 該類是 SpringMVC框架與 Tomcat整合時的一個關鍵類
 * Tomcat處理業務原生的都是調用繼承了 HttpServlet的類来完成，此時需要進行很多配置
 * 以及使用時要作很多重複性勞動。
 * SpringMVC框架提供的該類也是繼承了 HttpServlet的，使用它来接收處理請求的工作。
 */
public class DispatcherServlet {
    //這兩個資料夾是固定的，因此定義為靜態的，全局一份即可
    private static File baseDir;//類加載路徑
    private static File staticDir;//類加載路徑下的static資料夾

    private static DispatcherServlet instance = new DispatcherServlet();
    private DispatcherServlet(){}

    static {
        try {
            baseDir = new File(
                    ClientHandler.class.getClassLoader().getResource(".").toURI()
            );
            //定位類加載路徑中的static資料夾
            staticDir = new File(baseDir,"static");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    //實際上一個類繼承了 HttpServlet 要重寫方法: service,該方法用於處理請求
    public void service(HttpServletResponse response,HttpServletRequest request){
        //不能再使用uri作為請求路徑判斷了，因可為能包含参數，而參數内容不是固定值
        String path = request.getRequestURI();
        System.out.println("請求路徑: "+path);

        //首先判斷是否為業務
        if("/regUser".equals(path)){
            UserController controller = new UserController();
            controller.reg(request,response);
        }else if("/loginUser".equals(path)){
//            UserController controller = new UserController();
//            controller.login(request,response);
        }else {
            File file = new File(staticDir,path);
            if (file.isFile()){
                response.setStatusCode(200);
                response.setStatusReason("OK");
                response.setContentFile(file);
                response.addHeader("Server","BirdWebServer");

            }else {
                response.setStatusCode(404);
                response.setStatusReason("NotFound");
                file = new File(staticDir, "404.html");
                response.setContentFile(file);
                response.addHeader("Server", "BirdWebServer");
            }
        }
    }

    public static DispatcherServlet getInstance(){
        return instance;
    }
}
