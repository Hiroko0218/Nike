package com.tedu.springboot2304.controller;

import com.tedu.springboot2304.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**
 * 使用當前類處理與用戶相關的業務操作
 * 比如: 登入，註冊，修改密碼等等
 *
 * controller在 SpringBoot上的創建要求
 * 1:包必須要定義在啟動類所在的包中
 * 2:我們定義的 Controller類上必須要添加一个註解 "@Controller"
 *   不添加該注解，Spring框架不會調用這個類来處理業務
 * 3:添加處理某個請求的業務方法，並且方法上要添加註解 "@RequestMapping"
 *   且該註解上要添加一個参數，該參數值與其對應的請求路徑一致即可
 *   例如:
 *   reg.html頁面上表單們定義了 action="/regUser"，意味著當用戶在註冊頁面上
 *   輸入註冊信息後點擊註冊按鈕交表單時，表單提交的路徑為"/regUser"
 *   我們希望該請求路徑可以被 UserController中的 reg方法處理時，該方法上就要添加
 *   註解 @RequestMapping("/regUser") 這裡參數要與頁面表單中action值一致。
 *   此時該表單提交后 Spring框架就會自動調用到 reg方法来處理了
 */
@Controller
public class UserController {
    //表示保存所有用戶信息的資料夾users
    private static File userDir;
    static {
        userDir = new File("./users");
        if (!userDir.exists()){//測試此抽象路徑名表示的文件或資料夾是否存在。
            userDir.mkdirs();//創建由此抽象路徑名命名的資料夾，包括任何必需但不存在的父資料夾。
        }
    }

    @RequestMapping("/regUser")
    public void reg(HttpServletRequest request, HttpServletResponse response){
        System.out.println("開始處理註冊!!");
        /*
            1:獲取表單提交的註冊信息
            2:將用戶註冊信息保存在磁盤上
            3:回復用戶註冊结果頁面
         */

        //1
        //regUser?username=范传奇&password=123456&nickname=传奇&age=22
        /*
            HttpServletRequest為請求對象，保存著瀏覽器發送過來的所有内容
            我們通過請求對象獲取表單提交的數據時，使用如下方法:
            String getParameter(String name)
            該方法可以獲取瀏覽器提交的某個参數的值。
            實際應用中，我們在 getParameter中傳入的參數要與頁面表單中對應
            輸入框上name=""的值一致
            例如:
            註冊頁面，用戶名輸入框<input name="username" type="text">
            我們要獲取該輸入框的值，這裡需要:
             String username = request.getParameter("username");
         */
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String ageStr = request.getParameter("age");
        System.out.println(username+","+password+","+nickname+","+ageStr);

        /*
            對表單提交的數據做必要的驗證

            當我們使用: request.getParameter()獲取来自瀏覽器發送過来的一個
            參數時，返回值有三種情況
            1:正確的字符串内容，正常獲取到了對應的參數值
            2:null,request.getParameter("參數名")瀏覽器没有傳遞該參數
              例如:
              /regUser?username=XXX&password=XXX&nickname=XXX&age=XXX
              request.getParameter("user");//獲取名為user的參數
              由於瀏覽器提交的參數中不含有"user"這個參數，此時方法返回null
            3:空字符串,瀏覽器提交該參數時，該參數没有值
              例如:
              /regUser?username=&password=XXX&nickname=XXX&age=XXX
              request.getParameter("username");//返回值為空字符串
              參數没有值產生原因:
              用戶在該輸入框上什么也没有輸入
         */
        if (username==null||username.isEmpty()||
                password==null||password.isEmpty()||
                nickname==null||nickname.isEmpty()||
                ageStr==null||ageStr.isEmpty()||
                !ageStr.matches("[0-9]+")){
            //不能註冊的情況
            try {
                response.sendRedirect("/reg_info_error.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;//结束方法，不會再向下繼續執行註冊操作
        }

        /*
            2
            將該註冊用戶信息以一個User實例形式表示。
            將該對象序列化並寫入文件: 用户名.obj
         */
        int age = Integer.parseInt(ageStr);
        User user = new User(username,password,nickname,age);

        /*
            File的構造器
            File(File parent,String child)
            創建表示child的子資料夾，該子資料夾應當在parent表示的資料夾中
         */
        File userFile = new File(userDir,username+".obj");

        //驗證是否為重複用戶
        if (userFile.exists()){
            try {
                response.sendRedirect("/have_user.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        try (
                FileOutputStream fos = new FileOutputStream(userFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            oos.writeObject(user);
            response.sendRedirect("/reg_success.html");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
        定義login方法。
        要求:登入頁面表單提交後要調用到該方法。並且該方法中可以獲取表單提交的
        用戶名和密碼，將其輸出
        1:定義方法
        2:添加註解 @RequestMapping
        3:方法中通過request獲取表單數據
        4:打桩輸出
     */
    @RequestMapping("/loginUser")
    public void login(HttpServletRequest request,HttpServletResponse response){
        System.out.println("開始處理登入了!!");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username+","+password);

        //添加必要的驗證,用戶名或密碼沒輸入則跳轉 login_info_error.html
        if (username==null||username.isEmpty()||
                password==null||password.isEmpty()){
            try {
                response.sendRedirect("/login_info_error.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        //創建File對象使用該用戶名.obj作為文件名定位users資料夾下對應文件
        File userFile = new File(userDir,username+".obj");

        /*
            分支:
            如果該文件存在則說明用戶存在,需要反序列化讀取文件中的User對象,
            該對象記錄著該用戶曾經的註冊信息,此時讀取後比較密碼
                分支:
                密碼一致,則登入成功,跳轉: login_success.html
                密碼不一致,則登入失败,跳轉:login_fail.html

            如果該文件不存在說明没有这個注册用戶,這屬於登入失敗,跳轉:login_fail.html
         */

        if (userFile.exists()){
            try (
                    FileInputStream fis = new FileInputStream(userFile);
                    ObjectInputStream ois = new ObjectInputStream(fis);
            ){
                User user =(User) ois.readObject();
                //若密碼一致，則表示登入成功
                if (user.getPassword().equals(password)) {
                    response.sendRedirect("/login_success.html");
                }else{
                    response.sendRedirect("/login_fail.html");
                }
            } catch (IOException  | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            //沒有該用戶,登入失敗
            try {
                response.sendRedirect("/login_fail.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 生成展示所有用户信息的动态页面
     * @param request
     * @param response
     */
    @RequestMapping("/userList")
    public void userList(HttpServletResponse response,HttpServletRequest request){
        System.out.println("開始處理用戶列表的動態頁面!!!");
        /*
            1:讀取並反序列化users資料夾下的所有obj文件得到所有的註冊用戶的User對象
            2:生成一個html頁面並在表格中包含這些用戶信息
            3:將頁面發送给瀏覽器
         */
        //1
        //1.1先將users資料夾中的所有obj文件獲取回来
        File[] subs = userDir.listFiles(f->f.getName().endsWith(".obj"));

        //1.遍歷數組，對每一個文件進行反序列化
        List<User> userList = new ArrayList<>();//該集合保存所有註冊用戶
        for(File sub : subs){
            try (
                    FileInputStream fis = new FileInputStream(sub);
                    ObjectInputStream ois = new ObjectInputStream(fis);
            ){
                User user = (User)ois.readObject();
                userList.add(user);//將讀取的用戶對象存入集合
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        System.out.println(userList);

        try {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.println("<!DOCTYPE html>");
            pw.println("<html lang=\"en\">");
            pw.println("<head>");
            pw.println("<meta charset=\"UTF-8\">");
            pw.println("<title>用戶列表</title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<center>");
            pw.println("<h1>用戶列表</h1>");
            pw.println("<table border=\"1\">");
            pw.println("<tr><td>用戶名</td><td>密碼</td><td>暱稱</td><td>年齡</td></tr>");

            for(User user : userList) {
                pw.println("<tr>");
                pw.println("<td>"+user.getUsername()+"</td>");
                pw.println("<td>"+user.getPassword()+"</td>");
                pw.println("<td>"+user.getNickname()+"</td>");
                pw.println("<td>"+user.getAge()+"</td>");
                pw.println("</tr>");
            }

            pw.println("</table>");
            pw.println("</center>");
            pw.println("</body>");
            pw.println("</html>");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
