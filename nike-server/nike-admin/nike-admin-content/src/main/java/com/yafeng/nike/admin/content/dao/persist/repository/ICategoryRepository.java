package com.yafeng.nike.admin.content.dao.persist.repository;

import com.yafeng.nike.admin.content.pojo.entity.Category;
import com.yafeng.nike.admin.content.pojo.vo.CategoryListItemVO;
import com.yafeng.nike.admin.content.pojo.vo.CategoryStandardVO;
import com.yafeng.nike.common.pojo.vo.PageData;

import java.util.Collection;
import java.util.List;

/**
 * 處理類別數據的存儲庫接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface ICategoryRepository {

    /**
     * 插入類別數據
     *
     * @param category 類別數據
     * @return 受影響的行數
     */
    int insert(Category category);

    /**
     * 根據id刪除類別數據
     *
     * @param id 類別ID
     * @return 受影響的行數
     */
    int deleteById(Long id);

    /**
     * 根據若幹個ID批量刪除類別數據
     *
     * @param idList 若幹個類別ID的數組
     * @return 受影響的行數
     */
    int deleteByIds(Collection<Long> idList);

    /**
     * 根據id修改類別數據
     *
     * @param category 封裝了類別ID和新數據的對象
     * @return 受影響的行數
     */
    int update(Category category);

    /**
     * 統計類別表中的數據的數量
     *
     * @return 類別表中的數據的數量
     */
    int count();

    /**
     * 根據類別名稱統計當前表中類別數據的數量
     *
     * @param name 類別名稱
     * @return 當前表中匹配名稱的類別數據的數量
     */
    int countByName(String name);

    /**
     * 統計當前表中非此類別id的匹配名稱的類別數據的數量
     *
     * @param id   當前類別ID
     * @param name 類別名稱
     * @return 當前表中非此類別id的匹配名稱的類別數據的數量
     */
    int countByNameAndNotId(Long id, String name);

    /**
     * 根據id查詢類別數據詳情
     *
     * @param id 類別ID
     * @return 匹配的類別數據詳情，如果沒有匹配的數據，則返回null
     */
    CategoryStandardVO getStandardById(Long id);

    /**
     * 查詢類別數據列表
     *
     * @param pageNum  頁碼
     * @param pageSize 每頁記錄數
     * @return 類別數據列表
     */
    PageData<CategoryListItemVO> list(Integer pageNum, Integer pageSize);

}

