package io.ideploy.web.controller;

import io.ideploy.common.constants.ApiCode;
import io.ideploy.common.entity.RestResult;
import io.ideploy.web.entity.vo.Account;
import io.ideploy.web.service.account.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: code4china
 * @description:
 * @date: Created in 15:34 2018/12/21
 */
@Controller
public class CommonController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String  login() {
        return "login";
    }


    @RequestMapping(value = "/index.html",method = RequestMethod.GET)
    public String  index() {
        return "index";
    }
}
