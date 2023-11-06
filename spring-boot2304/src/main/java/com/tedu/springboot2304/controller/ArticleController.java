package com.tedu.springboot2304.controller;

import com.tedu.springboot2304.entity.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class ArticleController {
    public static File articleDir;

    static {
        articleDir = new File("./articles");
        if (!articleDir.exists()){
            articleDir.mkdirs();
        }
    }

    @RequestMapping("/writeArticle")
    public void writeArticle(HttpServletRequest request, HttpServletResponse response){
        System.out.println("開始處理發表文章!");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String content = request.getParameter("content");

        if (title==null||title.isEmpty()||
                author==null||author.isEmpty()||
                content==null||content.isEmpty()){
            try {
                response.sendRedirect("/article_fail.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        //將文章保存
        File articleFile = new File(articleDir,author+".ojb");
        if (articleFile.exists()){
            try {
                response.sendRedirect("/article_fail.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        Article article = new Article(title,author,content);

        try (FileOutputStream fos = new FileOutputStream(articleFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            oos.writeObject(article);
            response.sendRedirect("/article_success.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
