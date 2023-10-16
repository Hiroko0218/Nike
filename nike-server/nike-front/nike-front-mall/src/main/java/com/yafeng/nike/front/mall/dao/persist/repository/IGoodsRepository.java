package com.yafeng.nike.front.mall.dao.persist.repository;

import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.front.mall.pojo.entity.Goods;
import com.yafeng.nike.front.mall.pojo.vo.GoodsListItemVO;
import com.yafeng.nike.front.mall.pojo.vo.GoodsStandardVO;

/**
 * 處理商品數據的存儲庫接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface IGoodsRepository {

    /**
     * 根據id修改商品數據
     *
     * @param goods 封裝了商品ID和新數據的對象
     * @return 受影響的行數
     */
    int update(Goods goods);

    /**
     * 根據id查詢商品數據詳情
     *
     * @param id 商品ID
     * @return 匹配的商品數據詳情，如果沒有匹配的數據，則返回null
     */
    GoodsStandardVO getStandardById(Long id);

    /**
     * 查詢推薦的商品數據列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 商品數據列表
     */
    PageData<GoodsListItemVO> listByRecommend(Integer pageNum, Integer pageSize);

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
