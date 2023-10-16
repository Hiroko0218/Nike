package com.yafeng.nike.admin.mall.service.impl;

import com.yafeng.nike.admin.mall.dao.persist.repository.*;
import com.yafeng.nike.admin.mall.dao.search.IGoodsSearchRepository;
import com.yafeng.nike.admin.mall.pojo.entity.CheckLog;
import com.yafeng.nike.admin.mall.pojo.entity.Goods;
import com.yafeng.nike.admin.mall.pojo.entity.GoodsDetail;
import com.yafeng.nike.admin.mall.pojo.param.GoodsAddNewParam;
import com.yafeng.nike.admin.mall.pojo.vo.CategoryStandardVO;
import com.yafeng.nike.admin.mall.pojo.vo.GoodsListItemVO;
import com.yafeng.nike.admin.mall.pojo.vo.GoodsSearchVO;
import com.yafeng.nike.admin.mall.pojo.vo.GoodsStandardVO;
import com.yafeng.nike.admin.mall.service.IGoodsService;
import com.yafeng.nike.common.enumerator.ServiceCode;
import com.yafeng.nike.common.ex.ServiceException;
import com.yafeng.nike.common.pojo.authentication.CurrentPrincipal;
import com.yafeng.nike.common.pojo.vo.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 處理商品數據的業務實現類
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Slf4j
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Value("${nike.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;
    @Autowired
    private IGoodsRepository goodsRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IGoodsDetailRepository goodsDetailRepository;
    @Autowired
    private ICommentRepository commentRepository;
    @Autowired
    private ICheckLogRepository checkLogRepository;
    @Autowired
    private IGoodsSearchRepository goodsSearchRepository;

    public GoodsServiceImpl() {
        log.debug("創建業務類對象：GoodsServiceImpl");
    }

    @Override
    public void addNew(CurrentPrincipal currentPrincipal, String remoteAddr, GoodsAddNewParam goodsAddNewParam) {
        log.debug("開始處理【發布商品】的業務，當事人：{}，IP地址：{}，參數：{}", currentPrincipal, remoteAddr, goodsAddNewParam);

        Long categoryId = goodsAddNewParam.getCategoryId();
        CategoryStandardVO category = categoryRepository.getStandardById(categoryId);
        if (category == null) {
            String message = "發布商品失敗，類別數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if (category.getIsParent() != 0) {
            String message = "發布商品失敗，選擇的類別必須不包含子級類別！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        if (category.getEnable() != 1) {
            String message = "發布商品失敗，選擇的類別已經被禁用！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsAddNewParam, goods);
        goods.setCategoryName(category.getName());
        goods.setCheckState(0);
        goods.setIsRecommend(0);
        goods.setIsPutOn(0);
        goods.setSalesCount(0);
        goods.setCommentCount(0);
        goods.setPositiveCommentCount(0);
        goods.setNegativeCommentCount(0);
        int rows = goodsRepository.insert(goods);
        if (rows != 1) {
            String message = "發布商品失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }

        GoodsDetail goodsDetail = new GoodsDetail();
        goodsDetail.setGoodsId(goods.getId());
        goodsDetail.setDetail(goodsAddNewParam.getDetail());
        rows = goodsDetailRepository.insert(goodsDetail);
        if (rows != 1) {
            String message = "發布商品失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }
    }

    @Override
    public void delete(Long id) {
        log.debug("開始處理【根據ID刪除商品】的業務，參數：{}", id);
        GoodsStandardVO queryResult = goodsRepository.getStandardById(id);
        if (queryResult == null) {
            String message = "刪除商品失敗，嘗試刪除的商品數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        int rows = goodsRepository.deleteById(id);
        if (rows != 1) {
            String message = "刪除商品失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_DELETE, message);
        }

        rows = goodsDetailRepository.deleteByGoods(id);
        if (rows != 1) {
            String message = "刪除商品失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_DELETE, message);
        }

        commentRepository.deleteByGoods(id);
        checkLogRepository.deleteByResource(RESOURCE_TYPE_GOODS, id);
    }

    @Override
    public void passCheck(CurrentPrincipal currentPrincipal, Long goodsId, String remarks) {
        log.debug("開始處理【審核通過商品】的業務，參數：{}", goodsId);
        updateCheckById(currentPrincipal, goodsId, CHECK_STATE_PASS, remarks);
    }

    @Override
    public void rejectCheck(CurrentPrincipal currentPrincipal, Long goodsId, String remarks) {
        log.debug("開始處理【拒絕審核商品】的業務，參數：{}", goodsId);
        updateCheckById(currentPrincipal, goodsId, CHECK_STATE_REJECT, remarks);
    }

    @Override
    public void putOn(Long id) {
        log.debug("開始處理【上架商品】的業務，參數：{}", id);
        updatePutOnById(id, PUT_ON_STATE_ON);
    }

    @Override
    public void pullOff(Long id) {
        log.debug("開始處理【下架商品】的業務，參數：{}", id);
        updatePutOnById(id, PUT_ON_STATE_OFF);
    }

    @Override
    public void setRecommend(Long id) {
        log.debug("開始處理【推薦商品】的業務，參數：{}", id);
        updateRecommendById(id, RECOMMEND_STATE_ON);
    }

    @Override
    public void cancelRecommend(Long id) {
        log.debug("開始處理【取消推薦商品】的業務，參數：{}", id);
        updateRecommendById(id, RECOMMEND_STATE_OFF);
    }

    @Override
    public GoodsStandardVO getStandardById(Long id) {
        log.debug("開始處理【根據ID查詢商品】的業務，參數：{}", id);
        GoodsStandardVO queryResult = goodsRepository.getStandardById(id);
        if (queryResult == null) {
            String message = "查詢商品失敗，商品數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        return queryResult;
    }

    @Override
    public PageData<GoodsListItemVO> list(Integer pageNum) {
        log.debug("開始處理【查詢商品列表】的業務，頁碼：{}", pageNum);
        return goodsRepository.list(pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<GoodsListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("開始處理【查詢商品列表】的業務，頁碼：{}，每頁記錄數：{}", pageNum, pageSize);
        return goodsRepository.list(pageNum, pageSize);
    }

    @Override
    public PageData<GoodsListItemVO> listByCategory(Long categoryId, Integer pageNum) {
        log.debug("開始處理【根據類別查詢商品列表】的業務，商品類別：{}, 頁碼：{}", categoryId, pageNum);
        return goodsRepository.listByCategory(categoryId, pageNum, defaultQueryPageSize);
    }

    @Override
    public PageData<GoodsListItemVO> listByCategory(Long categoryId, Integer pageNum, Integer pageSize) {
        log.debug("開始處理【根據類別查詢商品列表】的業務，商品類別：{}, 頁碼：{}，每頁記錄數：{}", categoryId, pageNum, pageSize);
        return goodsRepository.listByCategory(categoryId, pageNum, pageSize);
    }

    @Override
    public void rebuildSearch() {
        log.debug("開始處理【重建商品的搜索數據】的業務");
        goodsSearchRepository.deleteAll();
        Integer pageNum = 1;
        Integer pageSize = 3;
        Integer maxPage;
        PageData<GoodsSearchVO> pageData;
        do {
            pageData = goodsRepository.listSearch(pageNum, pageSize);
            maxPage = pageData.getMaxPage();
            goodsSearchRepository.saveAll(pageData.getList());
            pageNum++;
        } while (pageNum <= maxPage);
    }

    private void updateCheckById(CurrentPrincipal currentPrincipal, Long id, Integer checkState, String remarks) {
        GoodsStandardVO currentGoods = goodsRepository.getStandardById(id);
        if (currentGoods == null) {
            String message = "將商品的審核狀態修改為【" + CHECK_STATE_TEXT[checkState] + "】失敗，商品數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if (currentGoods.getCheckState().equals(checkState)) {
            String message = "將商品的審核狀態修改為【" + CHECK_STATE_TEXT[checkState] + "】失敗，此商品已經處於" + CHECK_STATE_TEXT[checkState] + "狀態！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        int subStringEndIndex = currentGoods.getTitle().length() < BRIEF_MAX_LENGTH ? currentGoods.getTitle().length() : BRIEF_MAX_LENGTH;

        CheckLog checkLog = new CheckLog();
        checkLog.setResourceType(RESOURCE_TYPE_GOODS);
        checkLog.setResourceId(id);
        checkLog.setResourceBrief(currentGoods.getTitle().substring(0, subStringEndIndex));
        checkLog.setCheckUserId(currentPrincipal.getId());
        checkLog.setCheckUsername(currentPrincipal.getUsername());
        checkLog.setCheckRemarks(remarks);
        checkLog.setOriginalState(currentGoods.getCheckState());
        checkLog.setNewState(checkState);
        checkLog.setGmtCheck(LocalDateTime.now());
        int rows = checkLogRepository.insert(checkLog);
        if (rows != 1) {
            String message = "將商品的審核狀態修改為【" + CHECK_STATE_TEXT[checkState] + "】失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }

        Goods updateGoods = new Goods();
        updateGoods.setId(id);
        updateGoods.setCheckState(checkState);
        rows = goodsRepository.update(updateGoods);
        if (rows != 1) {
            String message = "將商品的審核狀態修改為【" + CHECK_STATE_TEXT[checkState] + "】失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    private void updatePutOnById(Long id, Integer isPutOn) {
        GoodsStandardVO currentGoods = goodsRepository.getStandardById(id);
        if (currentGoods == null) {
            String message = "將商品狀態修改為【" + PUT_ON_STATE_TEXT[isPutOn] + "】失敗，商品數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if (currentGoods.getIsPutOn().equals(isPutOn)) {
            String message = "將商品狀態修改為【" + PUT_ON_STATE_TEXT[isPutOn] + "】失敗，此商品已經處於" + PUT_ON_STATE_TEXT[isPutOn] + "狀態！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Goods updateGoods = new Goods();
        updateGoods.setId(id);
        updateGoods.setIsPutOn(isPutOn);
        int rows = goodsRepository.update(updateGoods);
        if (rows != 1) {
            String message = "將商品狀態修改為【" + PUT_ON_STATE_TEXT[isPutOn] + "】失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

    private void updateRecommendById(Long id, Integer isRecommend) {
        GoodsStandardVO currentGoods = goodsRepository.getStandardById(id);
        if (currentGoods == null) {
            String message = "將商品推薦狀態修改為【" + RECOMMEND_STATE_TEXT[isRecommend] + "】失敗，商品數據不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if (currentGoods.getIsRecommend().equals(isRecommend)) {
            String message = "將商品推薦狀態修改為【" + RECOMMEND_STATE_TEXT[isRecommend] + "】失敗，此商品已經處於" + RECOMMEND_STATE_TEXT[isRecommend] + "狀態！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        Goods updateGoods = new Goods();
        updateGoods.setId(id);
        updateGoods.setIsRecommend(isRecommend);
        int rows = goodsRepository.update(updateGoods);
        if (rows != 1) {
            String message = "將商品推薦狀態修改為【" + RECOMMEND_STATE_TEXT[isRecommend] + "】失敗，服務器忙，請稍後再嘗試！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }

}
