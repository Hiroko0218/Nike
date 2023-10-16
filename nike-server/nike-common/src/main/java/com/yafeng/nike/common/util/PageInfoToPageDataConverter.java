package com.yafeng.nike.common.util;

import com.github.pagehelper.PageInfo;
import com.yafeng.nike.common.pojo.vo.PageData;

/**
 * 將PageInfo轉換成PageData的轉換器工具類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public class PageInfoToPageDataConverter {

    /**
     * 將PageHelper框架中的PageInfo類型對象轉換成自定義的PageData類型對象
     *
     * @param pageInfo PageInfo對象
     * @param <T>      PageInfo對象中的列表數據中的元素數據的類型
     * @return 自定義的PageData類型的對象
     */
    public synchronized static <T> PageData<T> convert(PageInfo<T> pageInfo) {
        PageData<T> pageData = new PageData<>();
        pageData.setPageSize(pageInfo.getPageSize())
                .setTotal(pageInfo.getTotal())
                .setCurrentPage(pageInfo.getPageNum())
                .setMaxPage(pageInfo.getPages())
                .setList(pageInfo.getList());
        return pageData;
    }

}
