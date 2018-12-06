package com.tss.authserver.service;

import com.tss.authserver.feign.AccountService;
import com.tss.authserver.feign.vo.LoginUserInfoVO;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 根据用户名获取用户<br>
 * <p>
 * 密码校验请看下面两个类
 *
 * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
 * @see org.springframework.security.authentication.dao.DaoAuthenticationProvider
 */
@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {
    private static final Logger LOG = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUserInfoVO loginUserInfo = this.getUserInfo(username);
        if (loginUserInfo == null) {
            throw new AuthenticationCredentialsNotFoundException("用户不存在");
        }

        User user = new User(loginUserInfo.getUserAcc(), loginUserInfo.getPassword(), this.buildGrantedAuthority(loginUserInfo));
        return user;
    }
    
    private LoginUserInfoVO getUserInfo(String username) {
        // 为了支持多角色登录，这里username后面拼装上登录类型,如username|type
        String[] params = username.split("\\|");
        username = params[0];// 真正的用户名
        
        switch (params[1]) {
            case "1": 
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                return accountService.findStudentByUserAcc(username);
            default:
                throw new AuthenticationCredentialsNotFoundException("账号无效");
        }
        return null;
    }

    // 获取用户的所有权限并且SpringSecurity需要的集合
    private Collection<GrantedAuthority> buildGrantedAuthority(LoginUserInfoVO loginUserInfo) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        if (CollectionUtils.isNotEmpty(loginUserInfo.getRoles())) {
            loginUserInfo.getRoles().forEach(e -> {
                String role = (String) e;
                if (role.startsWith("ROLE_")) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(role));
                } else {
                    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                }
            });
        }

        if (CollectionUtils.isNotEmpty(loginUserInfo.getPermissions())) {
            loginUserInfo.getRoles().stream().forEach(e -> {
                grantedAuthorities.add(new SimpleGrantedAuthority((String) e));
            });
        }
        return grantedAuthorities;
    }

}
