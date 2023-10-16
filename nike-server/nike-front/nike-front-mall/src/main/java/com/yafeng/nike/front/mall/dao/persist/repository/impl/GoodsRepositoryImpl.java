package com.yafeng.nike.front.mall.dao.persist.repository.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yafeng.nike.common.pojo.vo.PageData;
import com.yafeng.nike.common.util.PageInfoToPageDataConverter;
import com.yafeng.nike.front.mall.dao.persist.mapper.GoodsMapper;
import com.yafeng.nike.front.mall.dao.persist.repository.IGoodsRepository;
import com.yafeng.nike.front.mall.pojo.entity.Goods;
import com.yafeng.nike.front.mall.pojo.vo.GoodsListItemVO;
import com.yafeng.nike.front.mall.pojo.vo.GoodsStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理商品數據的存儲庫實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Repository
public class GoodsRepositoryImpl implements IGoodsRepository {

    @Autowired
    private GoodsMapper goodsMapper;

    public GoodsRepositoryImpl() {
        log.info("創建存儲庫對象：GoodsRepositoryImpl");
    }

    @Override
    public int update(Goods goods) {
        log.debug("開始執行【更新商品】的數據訪問，參數：{}", goods);
        return goodsMapper.updateById(goods);
    }

    @Override
    public GoodsStandardVO getStandardById(Long id) {
        log.debug("開始執行【根據ID查詢商品信息】的數據訪問，參數：{}", id);
        return goodsMapper.getStandardById(id);
    }

    @Override
    public PageData<GoodsListItemVO> listByRecommend(Integer pageNum, Integer pageSize) {
        log.debug("開始執行【查詢推薦的商品數據列表】的數據訪問，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<GoodsListItemVO> list = goodsMapper.listByRecommend();
        PageInfo<GoodsListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

    @Override
    public PageData<GoodsListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize) {
        log.debug("開始執行【根據類別查詢商品列表】的數據訪問，商品類別：{}，頁碼：{}，每頁記錄數：{}", categoryId, pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<GoodsListItemVO> list = goodsMapper.listByCategory(categoryId);
        PageInfo<GoodsListItemVO> pageInfo = new PageInfo<>(list);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

}
