package com.yafeng.nike.admin.mall.dao.persist.repository;


import com.yafeng.nike.admin.mall.pojo.entity.Goods;
import com.yafeng.nike.admin.mall.pojo.vo.GoodsListItemVO;
import com.yafeng.nike.admin.mall.pojo.vo.GoodsSearchVO;
import com.yafeng.nike.admin.mall.pojo.vo.GoodsStandardVO;
import com.yafeng.nike.common.pojo.vo.PageData;

import java.util.Collection;

/**
 * 處理商品數據的存儲庫接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface IGoodsRepository {


    /**
     * 插入商品數據
     *
     * @param goods 商品數據
     * @return 受影響的行數
     */
    int insert(Goods goods);

    /**
     * 根據ID刪除商品數據
     *
     * @param id 商品ID
     * @return 受影響的行數
     */
    int deleteById(Long id);

    /**
     * 根據若幹個ID批量刪除商品數據
     *
     * @param idList 若幹個商品ID的數組
     * @return 受影響的行數
     */
    int deleteByIds(Collection<Long> idList);

    /**
     * 根據ID修改商品數據
     *
     * @param goods 封裝了商品ID和新數據的對象
     * @return 受影響的行數
     */
    int update(Goods goods);

    /**
     * 統計商品表中的數據的數量
     *
     * @return 商品表中的數據的數量
     */
    int count();

    /**
     * 根據類別統計商品數量
     *
     * @param categoryId 類別ID
     * @return 歸屬此類別下的商品數量
     */
    int countByCategory(Long categoryId);

    /**
     * 根據ID查詢商品數據詳情
     *
     * @param id 商品ID
     * @return 匹配的商品數據詳情，如果沒有匹配的數據，則返回null
     */
    GoodsStandardVO getStandardById(Long id);

    /**
     * 查詢商品數據列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 商品數據列表
     */
    PageData<GoodsListItemVO> list(Integer pageNum, Integer pageSize);

    /**
     * 查詢用於搜索的商品數據列表，此搜索結果將用於寫入到ES中
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 商品數據列表
     */
    PageData<GoodsSearchVO> listSearch(Integer pageNum, Integer pageSize);

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
