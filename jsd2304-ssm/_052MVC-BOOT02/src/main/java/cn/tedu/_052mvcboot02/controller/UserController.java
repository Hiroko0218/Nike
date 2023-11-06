package cn.tedu._052mvcboot02.controller;

import cn.tedu._052mvcboot02.mapper.UserMapper;
import cn.tedu._052mvcboot02.pojo.dto.UserAddDTO;
import cn.tedu._052mvcboot02.pojo.dto.UserUpdateDTO;
import cn.tedu._052mvcboot02.pojo.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @RestController注解：允許當前所有控制器方法以返回值的形式返回數據给客戶端
 * */
@RestController
@RequestMapping("/v1/users/")
public class UserController {

    /*
    required=false: 裝配不成功,也不報错! 慎用!!!!!!
    request=true(默認):裝配不成功,抛出異常!
     */
    @Autowired(required = false)
    private UserMapper userMapper;


    /** 添加用戶
     * method參數：限定請求方法
     * 等價於： @PostMapping注解
     */
//    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @PostMapping("insert")
//    @ResponseBody
    public String adduser(UserAddDTO userAddDTO){
        // 1.獲取DTO數據
        User user =new User();

        // 1-1.複製userAddDTO中所有屬性到user對象
        BeanUtils.copyProperties(userAddDTO, user);
        user.setCreated(new Date());

        // 2.在數據表中插入數據(MyBatis)
        int i = userMapper.insertUser(user);
        if (i>0){
            return "添加成功";
        }
        return "添加失敗";
    }


    /** 查詢用戶列表 */
    //@RequestMapping(value = "userList", method = RequestMethod.GET)
    @GetMapping("userList")
//    @ResponseBody
    public List<User> userList(){
//        List<User> list = new ArrayList<>();
//        User user = new User();
//        user.setUsername("xxx");
//        list.add(user);
//        System.out.println("list = " + list);
//        return list;
        return userMapper.selectUserList();
    }


    /** 刪除指定用戶 */
    //@RequestMapping(value = "delete", method = RequestMethod.GET)
    @GetMapping("delete")
//    @ResponseBody
    public String deleteUser(int id) {
        int i = userMapper.deleteUser(id);
        System.out.println("id = " + id);
        // 1.數據表中删除對應的數據
        if (i>0){
            return "删除成功";
        }
        return "刪除失敗";
    }


    /** 更新指定用戶 */
    //@RequestMapping(value = "update", method = RequestMethod.POST)
    @PostMapping("update")
//    @ResponseBody
    public String updateUser(UserUpdateDTO userUpdateDTO){
        System.out.println("userUpdateDTO = " + userUpdateDTO);
        // 1.創建空對象
        User user = new User();
        // 2.複製屬性
        BeanUtils.copyProperties(userUpdateDTO,user);
        // 3.執行更新語句
        userMapper.updateUser(user);
        return "修改成功";
    }
}
