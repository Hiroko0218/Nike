package cn.tedu.boot01.security;

import cn.tedu.boot01.mapper.UserMapper;
import cn.tedu.boot01.pojo.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserMapper mapper;

    @Override //此方法的username代表用戶輸入的用戶名
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
        /**
         * 此方法當Security框架進行登入認證時,自動調用
         * 當此方法響應null時 代表用戶名不存在, 下面模擬用戶輸入的用戶名是沒問題的
        */
        //假設tom和123456是從數據庫里面查詢出来的數據
//        if (username.equals("tom")){
//            UserDetails userDetails = User.builder()
//                    .username("tom").password("123456")
//                    .disabled(false)//帳號是否禁用
//                    .accountLocked(false)//帳號是否鎖定
//                    .accountExpired(false)//帳號是否過期
//                    .credentialsExpired(false)//登入憑證是否過期
//                    .authorities("權限名")//授予當前登入的用戶權限
//                    .build();
//            return userDetails;
//        }

        /* 從數據庫中查詢正確訊息 */
        UserVo userVo = mapper.selectByUsername(username);
        if (userVo!=null){
//
            CustomUserDetails userDetails=
                    new CustomUserDetails(
                            userVo.getId(),userVo.getNickname(),
                            username,userVo.getPassword(),
                            AuthorityUtils.createAuthorityList("我的權限")
                    );
            return userDetails;
        }
        return null;//代表用戶名不存在
    }
}
