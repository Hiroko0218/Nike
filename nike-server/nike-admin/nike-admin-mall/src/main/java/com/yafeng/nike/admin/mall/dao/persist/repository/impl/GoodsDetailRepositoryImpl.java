package com.yafeng.nike.admin.mall.dao.persist.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yafeng.nike.admin.mall.dao.persist.mapper.GoodsDetailMapper;
import com.yafeng.nike.admin.mall.dao.persist.repository.IGoodsDetailRepository;
import com.yafeng.nike.admin.mall.pojo.entity.GoodsDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 處理商品詳情數據的存儲庫實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Repository
public class GoodsDetailRepositoryImpl implements IGoodsDetailRepository {

    @Autowired
    private GoodsDetailMapper goodsDetailMapper;

    public GoodsDetailRepositoryImpl() {
        log.info("創建存儲庫對象：GoodsDetailRepositoryImpl");
    }

    @Override
    public int insert(GoodsDetail articleDetail) {
        log.debug("開始執行【插入商品詳情】的數據訪問，參數：{}", articleDetail);
        return goodsDetailMapper.insert(articleDetail);
    }

    @Override
    public int deleteByGoods(Long goodsId) {
        log.debug("開始執行【根據商品ID刪除商品詳情數據】的數據訪問，參數：{}", goodsId);
        QueryWrapper<GoodsDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id", goodsId);
        return goodsDetailMapper.delete(queryWrapper);
    }

    @Override
    public int updateByGoods(GoodsDetail articleDetail) {
        log.debug("開始執行【更新商品詳情】的數據訪問，參數：{}", articleDetail);
        QueryWrapper<GoodsDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id", articleDetail.getGoodsId());
        return goodsDetailMapper.update(articleDetail, queryWrapper);
    }

}
