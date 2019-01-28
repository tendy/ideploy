package io.ideploy.web.controller.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author code4china
 * @description
 * @date Created in 22:44 2019/1/18
 */
@Controller
@RequestMapping("/project")
public class ProjectGroupController {


    @RequestMapping(value = "/list_group.html",method = RequestMethod.GET)
    public String  list() {
        return "project/list_group";
    }

}
