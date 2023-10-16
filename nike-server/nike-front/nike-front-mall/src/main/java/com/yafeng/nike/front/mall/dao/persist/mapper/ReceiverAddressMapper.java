package com.yafeng.nike.front.mall.dao.persist.mapper;

import com.yafeng.nike.front.mall.pojo.entity.ReceiverAddress;
import com.yafeng.nike.front.mall.pojo.vo.ReceiverAddressListItemVO;
import com.yafeng.nike.front.mall.pojo.vo.ReceiverAddressStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 處理收貨地址數據的Mapper接口
 *
 * @author java@yafeng.com
 * @version 2.0
 */
@Repository
public interface ReceiverAddressMapper extends BaseMapper<ReceiverAddress> {

    /**
     * 根據id查詢收貨地址數據詳情
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
