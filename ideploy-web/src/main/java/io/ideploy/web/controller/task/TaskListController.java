package io.ideploy.web.controller.task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author code4china
 * @description
 * @date Created in 13:42 2019/1/28
 */
@Controller
@RequestMapping("/task")
public class TaskListController {

    @RequestMapping(value = "/list_task.html",method = RequestMethod.GET)
    public String  list() {
        return "task/list_task";
    }

}
