package com.tss.authserver.feign;

import com.tss.authserver.feign.vo.UserAuthInfoVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "${account.serviceId}")
public interface AccountService {

    @RequestMapping(value = "/student/info/getAuthInfoByUserAcc/{userAcc}", method = RequestMethod.GET)
    UserAuthInfoVO findStudentByUserAcc(@PathVariable("userAcc") String userAcc);

    @RequestMapping(value = "/teacher/info/getAuthInfoByUserAcc/{userAcc}", method = RequestMethod.GET)
    UserAuthInfoVO findTeacherByUserAcc(@PathVariable("userAcc") String userAcc);
}
