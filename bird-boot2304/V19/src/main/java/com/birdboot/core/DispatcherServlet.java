package com.birdboot.core;

import com.birdboot.annotation.Controller;
import com.birdboot.annotation.RequestMapping;
import com.birdboot.controller.UserController;
import com.birdboot.http.HttpServletRequest;
import com.birdboot.http.HttpServletResponse;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    public void service(HttpServletResponse response,HttpServletRequest request) {
        //不能再使用uri作為請求路徑判斷了，因可為能包含参數，而參數内容不是固定值
        String path = request.getRequestURI();
        System.out.println("請求路徑: " + path);

        //首先判斷是否為業務
        /*
            V17新增内容:
            當我們得到本次請求路徑path的值後，我們首先要查看是否為請求業務:
            1:掃描controller包下的所有類
            2:查看哪些被注解@Controller標註的過的類(只有被該注解標註的類才認可為業務處理類)
            3:遍歷這些類，並獲取他們的所有方法，並查看哪些時業務方法
              只有被注解@RequestMapping標註的方法才是業務方法
            4:遍歷業務方法時比對該方法上@RequestMapping中傳遞的參數值是否與本次請求
              路徑path值一致?如果一致則說明本次請求就應當由該方法進行處理
              因此利用反射機制調用該方法進行。
              提示:反射調用後要記得return,避免執行後續處理靜態資源的操作
            5:如果掃描了所有的Controller中所有的業務方法，均未找到與本次請求匹配的路徑
              則說明本次請求並非處理業務，那麼執行下面請求靜態資源的操作


            對於包而言我們只掃描com.birdboot.controller包

         */
        Method method = HandlerMapping.getMethod(path);
        if (method!=null){//如果獲取到方法說明本次請求為處理業務
            try {
                Class cls = method.getDeclaringClass();//根據當前方法對象獲取該方法所屬的類
                method.invoke(cls.newInstance(),request,response);
            } catch (Exception e) {
                //處理業務如果出縣異常，則應當给瀏覽器響應500，告知
                response.setStatusCode(500);
                response.setStatusReason("Internal Server Error");
            }
        }else {
            File file = new File(staticDir, path);
            if (file.isFile()) {
                response.setStatusCode(200);
                response.setStatusReason("OK");
                response.setContentFile(file);
                response.addHeader("Server", "BirdWebServer");
            } else {
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
