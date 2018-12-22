package io.ideploy.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: code4china
 * @description:
 * @date: Created in 15:34 2018/12/21
 */
@Controller
public class CommonController {

    @RequestMapping("/login.html")
    public String  login() {
        return "login";
    }


}
