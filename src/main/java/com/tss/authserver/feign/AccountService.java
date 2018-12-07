package com.tss.authserver.feign;

import com.tss.authserver.feign.vo.LoginUserInfoVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${account.serviceId}")
public interface AccountService {

    @RequestMapping(value = "/student/info/getLoginInfoByUserAcc/{userAcc}", method = RequestMethod.GET)
    LoginUserInfoVO findStudentByUserAcc(@PathVariable("userAcc") String userAcc);

}
