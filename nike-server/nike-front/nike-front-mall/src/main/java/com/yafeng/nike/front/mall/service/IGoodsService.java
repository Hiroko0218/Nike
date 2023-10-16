package com.yafeng.nike.front.mall.service;

import com.yafeng.nike.common.consts.data.MallConsts;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.front.mall.pojo.vo.GoodsListItemVO;
import com.yafeng.nike.front.mall.pojo.vo.GoodsStandardVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理商品數據的業務接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Transactional
public interface IGoodsService extends MallConsts {

    /**
     * 根據ID查詢商品
     *
     * @param id 商品ID
     * @return 匹配的商品數據詳情，如果沒有匹配的數據，則返回null
     */
    GoodsStandardVO getStandardById(Long id);

    /**
     * 查詢推薦的商品列表，將使用默認的每頁記錄數
     *
     * @param pageNum 頁碼
     * @return 商品列表
     */
    PageData<GoodsListItemVO> listByRecommend(Integer pageNum);

    /**
     * 查詢推薦的商品列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 商品列表
     */
    PageData<GoodsListItemVO> listByRecommend(Integer pageNum, Integer pageSize);

    /**
     * 根據類別查詢商品列表，將使用默認的每頁記錄數
     *
     * @param categoryId 商品類別的ID
     * @param pageNum    頁碼
     * @return 商品列表
     */
    PageData<GoodsListItemVO> listByCategory(Long categoryId, Integer pageNum);

    /**
     * 根據類別查詢商品列表
     *
     * @param categoryId 商品類別的ID
     * @param pageNum    頁碼
     * @param pageSize   每頁記錄數
     * @return 商品列表
     */
    PageData<GoodsListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize);

}
