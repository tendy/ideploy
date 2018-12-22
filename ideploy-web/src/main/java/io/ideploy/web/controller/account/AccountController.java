package io.ideploy.web.controller.account;

import io.ideploy.common.entity.RestResult;
import io.ideploy.web.entity.vo.Account;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author code4china
 * @description
 * @date Created in 00:29 2018/12/22
 */
@Controller
@RequestMapping("/account/")
public class AccountController {

    @RequestMapping("search.do")
    public RestResult<List<Account>> search(String name){
        return null;
    }

}
