package com.yafeng.nike.front.mall.service;

import com.yafeng.nike.common.pojo.authentication.CurrentPrincipal;
import com.yafeng.nike.front.mall.pojo.vo.CartListItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 處理購物車業務的接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Transactional
public interface ICartService {

    /**
     * 將商品添加到購物車
     *
     * @param currentPrincipal 當事人
     * @param goodsId          商品ID
     * @param goodsNum         商品數量
     */
    void add(CurrentPrincipal currentPrincipal, Long goodsId, Integer goodsNum);

    /**
     * 刪除購物車中的商品
     *
     * @param currentPrincipal 當事人
     * @param goodsId          商品ID
     */
    void delete(CurrentPrincipal currentPrincipal, Long goodsId);

    /**
     * 將購物車中商品的數量增加1
     *
     * @param currentPrincipal 當事人
     * @param goodsId          商品ID
     * @return 新的數量
     */
    Integer increaseNum(CurrentPrincipal currentPrincipal, Long goodsId);

    /**
     * 增加購物車中商品的數量
     *
     * @param currentPrincipal 當事人
     * @param goodsId          商品ID
     * @param num              增加的數量
     * @return 新的數量
     */
    Integer increaseNum(CurrentPrincipal currentPrincipal, Long goodsId, Integer num);

    /**
     * 將購物車中商品的數量減少1
     *
     * @param currentPrincipal 當事人
     * @param goodsId          商品ID
     * @return 新的數量
     */
    Integer reduceNum(CurrentPrincipal currentPrincipal, Long goodsId);

    /**
     * 減少購物車中商品的數量
     *
     * @param currentPrincipal 當事人
     * @param goodsId          商品ID
     * @param num              減少的數量
     * @return 新的數量
     */
    Integer reduceNum(CurrentPrincipal currentPrincipal, Long goodsId, Integer num);

    /**
     * 查詢購物車列表
     *
     * @param currentPrincipal 當事人
     * @return 購物車列表
     */
    List<CartListItemVO> list(CurrentPrincipal currentPrincipal);

}
