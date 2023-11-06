package cn.tedu.baking.controller;

import cn.tedu.baking.response.JsonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/v1/")
public class UploadController {

    @Value("${filePath}")
    private String dirPath;

    @PostMapping("upload")
    public JsonResult upload(MultipartFile file) throws IOException {
        //得到上傳圖片的名稱    a.jpg
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        //得到後綴.jpg
        String suffix =fileName.substring(fileName.lastIndexOf("."));
        //得到唯一的文件名，UUID.randomUUID()得到唯一標示符
        fileName = UUID.randomUUID()+suffix;
        System.out.println(fileName);

        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("/yyyy/MM/dd/");
        //得到日期相關的路徑      /2023/7/12/
        String datePath = simpleDateFormat.format(new Date());
        //創建文件對象
        File dirFile =new File(dirPath+datePath);
        //判斷如果文件不存在則創建
        if (!dirFile.exists()){
            dirFile.mkdirs();//創建
        }
        //把圖片保存到指定路徑 異常拋出
        file.transferTo(new File(dirPath+datePath+fileName));
        //上傳成功後，把圖片路徑響應給客戶端 /2023/07/12/xxxx.jpg
        return JsonResult.ok(datePath+fileName);
    }

    @PostMapping("remove")
    public JsonResult remove(String url){
        //刪除路徑對應的磁盤中文件
        new File(dirPath+url).delete();
        return JsonResult.ok();
    }
}