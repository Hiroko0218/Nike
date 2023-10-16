package com.yafeng.nike.admin.mall.dao.persist.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yafeng.nike.admin.mall.pojo.entity.Goods;
import com.yafeng.nike.admin.mall.pojo.vo.GoodsListItemVO;
import com.yafeng.nike.admin.mall.pojo.vo.GoodsSearchVO;
import com.yafeng.nike.admin.mall.pojo.vo.GoodsStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理商品數據的Mapper接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Repository
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 根據ID查詢商品
     *
     * @param id 商品ID
     * @return 匹配的商品，如果没有匹配的數據，則返回null
     */
    GoodsStandardVO getStandardById(Long id);

    /**
     * 查詢商品數據列表
     *
     * @return 商品數據列表
     */
    List<GoodsListItemVO> list();

    /**
     * 查詢用於搜索的商品數據列表，此搜索結果將用於寫入到ES中
     *
     * @return 商品數據列表
     */
    List<GoodsSearchVO> listSearch();

    /**
     * 根據類別查詢商品列表
     *
     * @param categoryId 商品類別的ID
     * @return 商品列表
     */
    List<GoodsListItemVO> listByCategory(Long categoryId);
}
