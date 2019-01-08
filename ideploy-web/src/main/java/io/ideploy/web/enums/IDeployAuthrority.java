package io.ideploy.web.enums;

/**
 * @author code4china
 * @description
 * @date Created in 23:00 2018/12/27
 */
public enum IDeployAuthrority {

    ROLE_LOGIN("ROLE_LOGIN", "登录用户"),
    ROLE_ADMIN("ROLE_ADMIN", "系统管理员"),

    ROLE_ACCOUNT_EDIT("ROLE_ACCOUNT_EDIT", "帐号编辑权限"),
    ROLE_ACCOUNT_VIEW("ROLE_ACCOUNT_VIEW", "帐号查询权限"),

    ROLE_DEPT_VIEW("ROLE_DEPT_VIEW", "部门信息查看"),
    ROLE_DEPT_EDIT("ROLE_DEPT_EDIT", "部门信息编辑"),

    ROLE_STAT_VIEW("ROLE_STAT_VIEW", "系统统计查看权限"),

    ROLE_PROJ_VIEW("ROLE_PROJ_VIEW", "项目信息查询权限"),
    ROLE_PROJ_EDIT("ROLE_PROJ_EDIT", "项目编辑保存权限"),
    ROLE_PROJ_ADMIN("ROLE_PROJ_ADMIN", "项目管理员"),

    ROLE_TASK_CREATE("ROLE_TASK_CREATE", "任务创建权限"),
    ROLE_TASK_AUDIT("ROLE_TASK_AUDIT", "任务审核权限"),
    ROLE_TASK_VIEW("ROLE_TASK_VIEW", "任务查看权限"),
    ROLE_TASK_EXEC("ROLE_TASK_EXEC", "任务执行权限");

    private String authority;

    private String desc;

    public String getAuthority() {
        return authority;
    }

    public String getDesc() {
        return desc;
    }

    private IDeployAuthrority(String authority, String desc){
        this.authority = authority;
        this.desc = desc;
    }

}
