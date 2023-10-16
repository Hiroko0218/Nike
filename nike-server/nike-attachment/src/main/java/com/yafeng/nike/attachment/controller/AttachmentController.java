package com.yafeng.nike.attachment.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.yafeng.nike.attachment.vo.UploadResult;
import com.yafeng.nike.common.enumerator.ServiceCode;
import com.yafeng.nike.common.ex.ServiceException;
import com.yafeng.nike.common.web.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 處理文件上傳的控制器類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@RestController
@RequestMapping("/upload")
@Api(tags = "  1. 文件上傳模塊")
public class AttachmentController {

    @Value("${nike.upload.host}")
    private String host;
    @Value("${nike.upload.base-dir-name}")
    private String baseDirName;
    @Value("${nike.upload.root-dir-name}")
    private String uploadRootDirName;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");

    /**
     * 保存用戶頭像的文件夾的名稱
     */
    public static final String DIR_NAME_USER_AVATAR = "user-avatar/";
    /**
     * 保存文章圖片的文件夾的名稱
     */
    public static final String DIR_NAME_IMAGE_ARTICLE = "article-image/";
    /**
     * 保存文章商品的文件夾的名稱
     */
    public static final String DIR_NAME_IMAGE_GOODS = "goods-image/";

    public AttachmentController() {
        log.debug("創建控制器類對象：ResourceController");
    }

    @PostMapping("/image/user-avatar")
    @ApiOperationSupport(order = 100)
    @ApiOperation("上傳用戶頭像")
    public JsonResult uploadUserAvatar(@RequestParam("file") MultipartFile multipartFile,
                                       @ApiIgnore @Value("${nike.upload.user-avatar.max-size}") Integer userAvatarMaxSize,
                                       @ApiIgnore @Value("${nike.upload.user-avatar.types}") List<String> userAvatarTypes) throws Throwable {
        log.debug("開始處理【上傳用戶頭像】的請求");
        UploadResult uploadResult = upload(multipartFile, userAvatarMaxSize, userAvatarTypes, DIR_NAME_USER_AVATAR);
        return JsonResult.ok(uploadResult);
    }

    @PostMapping("/image/article")
    @ApiOperationSupport(order = 210)
    @ApiOperation("上傳文章圖片")
    public JsonResult uploadArticleImage(@RequestParam("file") MultipartFile multipartFile,
                                         @ApiIgnore @Value("${nike.upload.article-image.max-size}") Integer articleImageMaxSize,
                                         @ApiIgnore @Value("${nike.upload.article-image.types}") List<String> articleImageTypes) throws Throwable {
        log.debug("開始處理【上傳文章圖片】的請求");
        UploadResult uploadResult = upload(multipartFile, articleImageMaxSize, articleImageTypes, DIR_NAME_IMAGE_ARTICLE);
        return JsonResult.ok(uploadResult);
    }

    @PostMapping("/image/goods")
    @ApiOperationSupport(order = 220)
    @ApiOperation("上傳商品圖片")
    public JsonResult uploadGoodsImage(@RequestParam("file") MultipartFile multipartFile,
                                       @ApiIgnore @Value("${nike.upload.goods-image.max-size}") Integer goodsImageMaxSize,
                                       @ApiIgnore @Value("${nike.upload.goods-image.types}") List<String> goodsImageTypes) throws Throwable {
        log.debug("開始處理【上傳商品圖片】的請求");
        UploadResult uploadResult = upload(multipartFile, goodsImageMaxSize, goodsImageTypes, DIR_NAME_IMAGE_GOODS);
        return JsonResult.ok(uploadResult);
    }

    /**
     * 上傳文件
     *
     * @param multipartFile   客戶端提交的文件數據
     * @param maxSize         允許的最大文件尺寸，單位：MB
     * @param validTypes      允許的文件類型
     * @param fileTypeDirName 存放此類文件的文件夾
     * @return 上傳成功的文件的相關信息
     * @throws IOException 將文件保存到服務器端時可能出現的讀寫磁盤異常
     */
    private UploadResult upload(MultipartFile multipartFile, long maxSize, List<String> validTypes, String fileTypeDirName) throws IOException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            String message = "上傳失敗，請選擇您要上傳的文件！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPLOAD_EMPTY, message);
        }

        long size = multipartFile.getSize();
        if (size > maxSize * 1024 * 1024) {
            String message = "上傳失敗，文件大小不允許超過" + maxSize + "MB！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPLOAD_EXCEED_MAX_SIZE, message);
        }

        String contentType = multipartFile.getContentType();
        if (!validTypes.contains(contentType)) {
            String message = "上傳失敗，僅允許上傳以下類型的文件：" + validTypes;
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPLOAD_INVALID_TYPE, message);
        }

        File uploadBaseDir = new File(uploadRootDirName, baseDirName);
        File articleImageDir = new File(uploadBaseDir, fileTypeDirName);
        String dateDirName = simpleDateFormat.format(new Date());
        File uploadDir = new File(articleImageDir, dateDirName);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String newFileName = UUID.randomUUID().toString();
        String originalFilename = multipartFile.getOriginalFilename();
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFullFileName = newFileName + suffix;
        File newFile = new File(uploadDir, newFullFileName);

        multipartFile.transferTo(newFile);
        log.debug("文件已經保存到：{}", newFile.getAbsoluteFile());

        String url = new StringBuilder()
                .append(host)
                .append(baseDirName)
                .append(fileTypeDirName)
                .append(dateDirName)
                .append(newFullFileName)
                .toString();

        UploadResult uploadResult = new UploadResult();
        uploadResult.setUrl(url);
        uploadResult.setFileSize(size);
        uploadResult.setContentType(contentType);
        uploadResult.setFileName(newFullFileName);
        return uploadResult;
    }

}
