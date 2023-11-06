package cn.tedu.egmvc2.mapper;

import cn.tedu.egmvc2.pojo.entity.Address;

import java.util.List;

public interface AddMapper {
    int instAdd(Address add);

    List<Address> selectAdd();

    int deleteAdd(int id);

    int updateAdd(Address add);
}
