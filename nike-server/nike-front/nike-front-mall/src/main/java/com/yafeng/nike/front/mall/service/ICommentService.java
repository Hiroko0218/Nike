package com.yafeng.nike.front.mall.service;

import com.yafeng.nike.common.consts.data.MallConsts;
import com.yafeng.nike.common.pojo.authentication.CurrentPrincipal;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.front.mall.pojo.param.CommentAddNewParam;
import com.yafeng.nike.front.mall.pojo.vo.CommentListItemVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * 處理評論數據的業務接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Transactional
public interface ICommentService extends MallConsts {

    /**
     * 發表評論
     *
     * @param currentPrincipal   當事人
     * @param commentAddNewParam 新增的評論數據
     */
    void addNewGoodsComment(CurrentPrincipal currentPrincipal,String remoteAddr, CommentAddNewParam commentAddNewParam);

    /**
     * 查詢商品的評論列表，將使用默認的每頁記錄數
     *
     * @param goodsId 商品ID
     * @param pageNum 頁碼
     * @return 商品的評論列表
     */
    PageData<CommentListItemVO> list(Long goodsId, Integer pageNum);

    /**
     * 查詢商品的評論數據列表
     *
     * @param goodsId  商品ID
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 商品的評論列表
     */
    PageData<CommentListItemVO> list(Long goodsId, Integer pageNum, Integer pageSize);

}
