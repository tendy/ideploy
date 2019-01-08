package io.ideploy.web.controller;

import io.ideploy.common.constants.ApiCode;
import io.ideploy.common.entity.RestResult;
import io.ideploy.web.entity.vo.Account;
import io.ideploy.web.service.account.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: code4china
 * @description:
 * @date: Created in 15:34 2018/12/21
 */
@Controller
public class CommonController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/login.html")
    public String  index() {
        return "login";
    }


    @RequestMapping("login.do")
    public RestResult login(String userName, String password){
        if(StringUtils.isBlank(userName) || StringUtils.isBlank(password)){
            return new RestResult(ApiCode.PARAMETER_ERROR, "参数不合法");
        }
        Account account = accountService.login(userName, password);
        if(account == null){
            return new RestResult(ApiCode.FAILURE, "用户名或密码不正确");
        }
        return new RestResult(ApiCode.SUCCESS, "登录成功");
    }
}
