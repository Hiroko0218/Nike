package com.yafeng.nike.front.mall.dao.persist.repository;

import com.yafeng.nike.front.mall.pojo.entity.ReceiverAddress;
import com.yafeng.nike.front.mall.pojo.vo.ReceiverAddressListItemVO;
import com.yafeng.nike.front.mall.pojo.vo.ReceiverAddressStandardVO;

import java.util.List;

/**
 * 處理收貨地址數據的存儲庫接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
public interface IReceiverAddressRepository {

    /**
     * 插入收貨地址數據
     *
     * @param receiverAddress 收貨地址數據
     * @return 受影響的行數
     */
    int insert(ReceiverAddress receiverAddress);

    /**
     * 根據ID刪除收貨地址數據
     *
     * @param id 收貨地址ID
     * @return 受影響的行數
     */
    int deleteById(Long id);

    /**
     * 根據ID修改收貨地址數據
     *
     * @param receiverAddress 封裝了收貨地址ID和新數據的對象
     * @return 受影響的行數
     */
    int update(ReceiverAddress receiverAddress);

    /**
     * 將用戶的所有收貨地址設置為非默認
     *
     * @param userId 用戶ID
     * @return 受影響的行數
     */
    int updateNonDefaultByUser(Long userId);

    /**
     * 根據ID查詢收貨地址數據詳情
     *
     * @param id 收貨地址ID
     * @return 匹配的收貨地址數據詳情，如果沒有匹配的數據，則返回null
     */
    ReceiverAddressStandardVO getStandardById(Long id);

    /**
     * 根據用戶查詢收貨地址列表
     *
     * @param userId 收貨地址類別的ID
     * @return 收貨地址列表
     */
    List<ReceiverAddressListItemVO> listByUser(Long userId);

}
