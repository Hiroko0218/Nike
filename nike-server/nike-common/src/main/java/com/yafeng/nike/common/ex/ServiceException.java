package com.yafeng.nike.common.ex;


import com.yafeng.nike.common.enumerator.ServiceCode;
import lombok.Getter;

/**
 * 業務異常
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public class ServiceException extends RuntimeException {

    @Getter
    private ServiceCode serviceCode;

    /**
     * 創建業務異常對象
     *
     * @param serviceCode 業務狀態碼
     * @param message     描述文本
     */
    public ServiceException(ServiceCode serviceCode, String message) {
        super(message);
        this.serviceCode = serviceCode;
    }

}
