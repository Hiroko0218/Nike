package com.yafeng.nike.admin.mall.service;

import com.yafeng.nike.admin.mall.pojo.param.GoodsAddNewParam;
import com.yafeng.nike.admin.mall.pojo.vo.GoodsListItemVO;
import com.yafeng.nike.admin.mall.pojo.vo.GoodsStandardVO;
import com.yafeng.nike.common.consts.data.MallConsts;
import com.yafeng.nike.common.pojo.authentication.CurrentPrincipal;
import com.yafeng.nike.common.pojo.vo.PageData;
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
     * 發布商品
     *
     * @param currentPrincipal 當事人
     * @param remoteAddr       IP地址
     * @param goodsAddNewParam 新的商品數據
     */
    void addNew(CurrentPrincipal currentPrincipal, String remoteAddr, GoodsAddNewParam goodsAddNewParam);

    /**
     * 根據ID刪除商品
     *
     * @param id 嘗試刪除的商品數據的ID
     */
    void delete(Long id);

    /**
     * 審核通過商品
     *
     * @param currentPrincipal 當事人
     * @param goodsId          嘗試審核通過的商品的ID
     * @param remarks          備注信息
     */
    void passCheck(CurrentPrincipal currentPrincipal, Long goodsId, String remarks);

    /**
     * 拒絕審核商品
     *
     * @param currentPrincipal 當事人
     * @param goodsId          嘗試拒絕審核的商品的ID
     * @param remarks          備注信息
     */
    void rejectCheck(CurrentPrincipal currentPrincipal, Long goodsId, String remarks);

    /**
     * 上架商品
     *
     * @param id 商品ID
     */
    void putOn(Long id);

    /**
     * 下架商品
     *
     * @param id 商品ID
     */
    void pullOff(Long id);

    /**
     * 推薦商品
     *
     * @param id 商品ID
     */
    void setRecommend(Long id);

    /**
     * 取消推薦商品
     *
     * @param id 商品ID
     */
    void cancelRecommend(Long id);

    /**
     * 根據ID查詢商品
     *
     * @param id 商品ID
     * @return 匹配的商品數據詳情，如果沒有匹配的數據，則返回null
     */
    GoodsStandardVO getStandardById(Long id);

    /**
     * 查詢商品列表，將使用默認的每頁記錄數
     *
     * @param pageNum 頁碼
     * @return 商品列表
     */
    PageData<GoodsListItemVO> list(Integer pageNum);

    /**
     * 查詢商品列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 商品列表
     */
    PageData<GoodsListItemVO> list(Integer pageNum, Integer pageSize);

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

    /**
     * 重建商品的搜索數據（更新ES中的商品數據）
     */
    void rebuildSearch();

}

