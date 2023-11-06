package cn.tedu._053mvcweibo.exception;

import cn.tedu._053mvcweibo.common.response.JsonResult;
import cn.tedu._053mvcweibo.common.response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ControllerAdvice: 標示當前類為全局異常處理器類型,
 *    可以捕獲和處理Controller中抛出的異常。
 * RestControllerAdvice:複合注解,等價於 ControllerAdvice 和 ResponseBody 注解
 * 當某個Controller中出現了異常,系统底層會查找有没有定義全局異常處理器對象;
 * 以及全局異常處理器對象中有没有定義對應的異常處理方法,如果有則調用此方法處理異常。
 */
//@ControllerAdvice
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * ExceptionHandler：標示當前方法為異常處理方法,參數為捕獲到的異常的對象
     * @param ex 具體的異常對象
     * ex.getMessage()方法: 獲取異常中的提示消息
     */
    @ExceptionHandler
    public JsonResult doHandleRuntimeException(RuntimeException ex){
        log.error("error is: " + ex.getMessage());
        return new JsonResult(StatusCode.OPERATION_FAILED, ex.getMessage());
    }

    /**
     * 當Controller中出現異常時,會優先在全局異常處理器中使用對應異常的處理方法處理異常;
     * 如果没有對應的異常處理方法,則會找該異常的父類異常處理方法處理該異常。
     */
    @ExceptionHandler
    public JsonResult doHandleIllegalArgumentException(IllegalArgumentException ex){
        log.error("IllegalArgumentException error is: " + ex.getMessage());
        return new JsonResult(StatusCode.OPERATION_FAILED, ex.getMessage());
    }

    /**
     * Validation中参數校驗失敗的異常處理方法
     * @param ex 異常對象
     * @return
     */
    @ExceptionHandler
    public JsonResult doHandleArgumentNotValidException(MethodArgumentNotValidException ex){
        /*
          ex.getFieldError().getDefaultMessage())：獲取valitation中的異常消息
         */
        return new JsonResult(StatusCode.VALIDATE_ERROR, ex.getFieldError().getDefaultMessage());
    }

    /**
     * 捕獲所有異常並處理
     */
//    @ExceptionHandler
//    public JsonResult doHandleThrowable(Throwable ex){
//        log.error("程式運行中出現Throwable");
//        return new JsonResult(StatusCode.OPERATION_FAILED, "程式運行中出现Throwable");
//    }
}
