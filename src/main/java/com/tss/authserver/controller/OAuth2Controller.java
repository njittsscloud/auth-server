package com.tss.authserver.controller;

import com.tss.basic.site.user.annotation.UserAuthInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth2")
public class OAuth2Controller {
    private static final Logger LOG = LoggerFactory.getLogger(OAuth2Controller.class);

    @Autowired
    private ConsumerTokenServices tokenServices;

    /**
     * 当前登陆用户信息<br>
     * <p>
     * security获取当前登录用户的方法是SecurityContextHolder.getContext().getAuthentication()<br>
     * 返回值是接口org.springframework.security.core.Authentication，又继承了Principal<br>
     * 这里的实现类是org.springframework.security.oauth2.provider.OAuth2Authentication<br>
     *
     * @return
     */
    @GetMapping("/user-me")
    public UserAuthInfo principal() {
        UserAuthInfo userAuthInfo = new UserAuthInfo();
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            userAuthInfo.setUserAcc(authentication.getName());
            return userAuthInfo;
        }
        return userAuthInfo;
    }

    
    /**
     * 注销登陆/退出
     * 移除access_token和refresh_token<br>
     * 用ConsumerTokenServices，该接口的实现类DefaultTokenServices已有相关实现
     *
     * @param access_token
     */
    @PostMapping(value = "/remove_token")
    public void removeToken(@RequestParam("access_token") String access_token) {
        tokenServices.revokeToken(access_token);
    }

}
