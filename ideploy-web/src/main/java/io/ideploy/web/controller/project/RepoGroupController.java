package io.ideploy.web.controller.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author code4china
 * @description
 * @date Created in 13:38 2019/1/28
 */
@Controller
@RequestMapping("/project")
public class RepoGroupController {

    @RequestMapping(value = "/list_repo.html",method = RequestMethod.GET)
    public String  list() {
        return "project/list_repo";
    }


}
