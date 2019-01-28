
CREATE DATABASE IF NOT EXISTS `ideploy` DEFAULT CHARACTER SET = utf8mb4;

use `ideploy`;

SET NAMES utf8mb4;

-- 说明：
-- (1) 除了 bool 字段，一般字段不要取值 0
-- (2) status类的字段，统一用 1 表示成功

CREATE TABLE IF NOT EXISTS `t_account` (
   `uid`           BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
   `account_id`    VARCHAR(50) NOT NULL COMMENT '帐号ID，英文字母数字',
   `password`      VARCHAR(128) NOT NULL COMMENT '密码',
   `type`          TINYINT NOT NULL DEFAULT '1' COMMENT '1-普通帐号，2-ldap帐号，5-超级管理员',
   `nick`          VARCHAR(50) NOT NULL COMMENT '帐号昵称',
   `phone`         VARCHAR(20) NOT NULL DEFAULT '' COMMENT '电话号码',
   `email`         VARCHAR(128) NOT NULL DEFAULT '' COMMENT '邮箱地址',
   `dding`         VARCHAR(80) NOT NULL DEFAULT '' COMMENT '用户钉钉号',
   `create_time`   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `update_time`   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   `creator`       BIGINT NOT NULL COMMENT '创建者',
   `status`        TINYINT NOT NULL DEFAULT 1 COMMENT '1: 正常, 2: 冻结',
    PRIMARY KEY (`uid`),
    UNIQUE KEY `uniq_key_accountId_type` (`account_id`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE IF NOT EXISTS t_account_group (
    acc_group_id   INT NOT NULL AUTO_INCREMENT COMMENT '主键',
    group_name     VARCHAR(40) NOT NULL COMMENT '用户组名称',
    create_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (acc_group_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户组';

CREATE TABLE IF NOT EXISTS t_account_group_member (
    acc_group_id   INT NOT NULL,
    uid            BIGINT NOT NULL COMMENT '用户',
    PRIMARY KEY (acc_group_id, uid),
    KEY key_uid (uid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户组成员';

CREATE TABLE IF NOT EXISTS t_env (
    env_id         INT NOT NULL AUTO_INCREMENT,
    env_name       VARCHAR(20) NOT NULL COMMENT '环境名称',
    prod           TINYINT NOT NULL DEFAULT 0 COMMENT '是否是生产环境',
    create_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    creator        BIGINT NOT NULL COMMENT '创建者',
    PRIMARY KEY (env_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '环境定义';

CREATE TABLE IF NOT EXISTS t_project (
    project_id     INT NOT NULL AUTO_INCREMENT,
    project_name   VARCHAR(40) NOT NULL COMMENT '项目名称',
    `language`     VARCHAR(20) NOT NULL COMMENT '项目语言，比如java',
    manager_id     BIGINT NOT NULL COMMENT '负责人uid',
    repo_id        INT NOT NULL COMMENT '仓库ID',
    run_user       VARCHAR(40) NOT NULL COMMENT '项目进程的运行用户，比如 www-data',
    copy_path      VARCHAR(255) NOT NULL COMMENT '存放路径(把文件拷贝到服务器哪个目录)，比如 /data/release/pay/v1, /data/release/pay/v2',
    target_path    VARCHAR(255) NOT NULL COMMENT '部署到服务器哪个目录(soft link)，目标服务器不要建立这个目录, 比如 /data/pay, 实际是: ln -s /data/release/pay/v1 /data/pay',
    checkout_path  VARCHAR(255) COMMENT '代码在服务器上checkout的目录，比如 /data/ideploy',
    pre_checkout   VARCHAR(2048) DEFAULT '' COMMENT '在checkout代码之前执行的shell代码',
    post_checkout  VARCHAR(2048) DEFAULT '' COMMENT '在checkout代码之后执行的shell，比如编译代码',
    pre_release    VARCHAR(2048) DEFAULT '' COMMENT '在同步完代码后(未修改版本目录)执行的shell代码, 比如stop服务，摘除节点',
    post_release   VARCHAR(2048) DEFAULT '' COMMENT '在部署完成后&修改版本目录后执行的shell代码, 比如start服务，加回节点',
    prod_audit     TINYINT NOT NULL DEFAULT 0 COMMENT '生产环境是否需要审核',
    transfer_mode  VARCHAR(20) NOT NULL DEFAULT 'ssh' COMMENT '数据传输方式: ssh/ansible',
    create_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    creator        BIGINT NOT NULL COMMENT '创建者',
    PRIMARY KEY (project_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '项目定义';

CREATE TABLE IF NOT EXISTS t_project_group (
    group_id       INT NOT NULL AUTO_INCREMENT COMMENT '项目组ID',
    group_name     VARCHAR(40) NOT NULL COMMENT '项目组名称',
    audit_email    TINYINT NOT NULL DEFAULT 0 COMMENT '审核发送email通知',
    audit_dingding TINYINT NOT NULL DEFAULT 0 COMMENT '审核发送钉钉通知',
    create_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (group_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '项目组';

CREATE TABLE IF NOT EXISTS t_repository (
    repo_id        INT NOT NULL AUTO_INCREMENT,
    repo_name      VARCHAR(40) NOT NULL COMMENT '仓库名称',
    `type`         TINYINT NOT NULL COMMENT '类型: 1: git, 2: svn',
    group_id       INT NOT NULL COMMENT '属于哪个项目组',
    url            VARCHAR(255) NOT NULL COMMENT '地址',
    username       VARCHAR(40) NOT NULL DEFAULT '' COMMENT '帐号，这里填写负责人的帐号，不要使用公共帐号',
    password       VARCHAR(128) NOT NULL DEFAULT '' COMMENT '密码(加密)',
    ssh_key        VARCHAR(2048) NOT NULL DEFAULT '' COMMENT 'git的ssh密钥',
    create_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    creator        BIGINT NOT NULL COMMENT '创建者',
    PRIMARY KEY (repo_id),
    KEY key_group_id (group_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '项目组仓库定义，只能在项目组内访问';

CREATE TABLE IF NOT EXISTS t_project_group_detail (
    group_id       INT NOT NULL COMMENT '项目组ID',
    project_id     INT NOT NULL COMMENT '项目ID',
    PRIMARY KEY (group_id, project_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '项目组的项目';

CREATE TABLE IF NOT EXISTS t_project_group_member (
    group_id       INT NOT NULL COMMENT '项目组ID',
    uid            BIGINT NOT NULL COMMENT '成员ID',
    role           TINYINT NOT NULL COMMENT '1: 负责人, 2: 成员, 3: 审核(系统自动管理)',
    PRIMARY KEY (group_id, uid),
    KEY key_uid (uid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '项目组成员';

CREATE TABLE IF NOT EXISTS t_project_audit (
    audit_id       INT NOT NULL AUTO_INCREMENT,
    project_id     INT NOT NULL COMMENT '项目ID',
    step           INT NOT NULL COMMENT '审核步骤，从小到大排列',
    step_name      VARCHAR(40) NOT NULL COMMENT '步骤名称，比如: 主管审核',
    PRIMARY KEY (audit_id),
    UNIQUE KEY ukey_project_id_step (project_id, step)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '项目部署审核步骤';

CREATE TABLE IF NOT EXISTS t_project_auditor (
    audit_id       INT NOT NULL,
    auditor_id     BIGINT NOT NULL COMMENT '审核者ID，可以是 uid 或 acc_group_id，特定的ID表示创建者自己',
    auditor_type   TINYINT NOT NULL DEFAULT 1 COMMENT '审核者类型：1-用户；2-用户组',
    PRIMARY KEY (audit_id, auditor_id, auditor_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '项目部署审核者，每个步骤可以多个审核者';


-- 添加服务器的时候，自动增加 t_server 记录即可，暂时不需要做管理界面
CREATE TABLE IF NOT EXISTS t_server (
    server_id      INT NOT NULL AUTO_INCREMENT,
    ip             VARCHAR(50) NOT NULL COMMENT 'IP地址，如果有多个IP，填写一个即可，比如内网IP',
    server_name    VARCHAR(100) NOT NULL COMMENT '服务器名称',
    create_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (server_id),
    UNIQUE KEY ukey_ip (ip)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '服务器定义';

CREATE TABLE IF NOT EXISTS t_server_group (
    group_id       INT NOT NULL AUTO_INCREMENT,
    group_name     VARCHAR(50) NOT NULL COMMENT '服务器名称',
    env_id         INT NOT NULL COMMENT '环境',
    project_id     INT NOT NULL COMMENT '所属项目',
    create_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY(group_id),
    KEY key_project_id (project_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '服务器组定义';

CREATE TABLE IF NOT EXISTS t_server_group_detail (
    group_id       INT NOT NULL COMMENT '服务器组ID',
    server_id      INT NOT NULL COMMENT '服务器ID',
    PRIMARY KEY(group_id, server_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '服务器组拥有的服务器';

CREATE TABLE IF NOT EXISTS t_task (
    task_id        INT NOT NULL AUTO_INCREMENT,
    project_id     INT NOT NULL COMMENT '所属项目',
    title          VARCHAR(100) NOT NULL COMMENT '发布版本的标题',
    uid            BIGINT NOT NULL COMMENT '任务创建者',
    auditor_step   INT NOT NULL COMMENT '到了审核的哪一步',
    branch         VARCHAR(60) NOT NULL COMMENT '发布的分支，比如 master',
    version_no     VARCHAR(30) NOT NULL COMMENT '发布的版本号',
    result         TINYINT NOT NULL COMMENT '1 是全部成功 2是部分成功 3是全部失败',
    restart        TINYINT NOT NULL DEFAULT 0 COMMENT '是否为重启服务器',
    `rollback`     TINYINT NOT NULL DEFAULT 0 COMMENT '是否为回滚操作',
    env_id         INT NOT NULL COMMENT '环境',
    deploy_servers INT NOT NULL COMMENT '部署的服务器数量',
    success_count  INT NOT NULL COMMENT '成功的数量',
    create_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (task_id),
    KEY key_project_id (project_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '部署任务';

-- 在新建任务的时候，把所有记录初始化，修改项目的审核步骤不影响已有的记录。
CREATE TABLE IF NOT EXISTS t_task_audit (
    task_audit_id  INT NOT NULL AUTO_INCREMENT,
    task_id        INT NOT NULL COMMENT '部署任务',
    project_id     INT NOT NULL COMMENT '项目',
    step           INT NOT NULL COMMENT '步骤',
    status         TINYINT NOT NULL COMMENT '审核状态, 0: 未进行, 1: 已审核',
    auditor_id     BIGINT NOT NULL COMMENT '审核者ID，可以是 uid 或 acc_group_id',
    auditor_type   TINYINT NOT NULL DEFAULT 1 COMMENT '审核者类型：1-用户；2-用户组',
    remark         VARCHAR(100) NOT NULL DEFAULT '' COMMENT '审核意见',
    create_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    audit_time     DATETIME DEFAULT NULL COMMENT '审核时间',
    ip             VARCHAR(50) DEFAULT '' NOT NULL COMMENT '审核时的IP',
    PRIMARY KEY (task_audit_id),
    KEY key_task_id_step (task_id, step),
    KEY key_auditor_id_type_status (auditor_id, auditor_type, status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '部署任务审核记录';

CREATE TABLE IF NOT EXISTS t_task_server (
    id             INT NOT NULL AUTO_INCREMENT,
    task_id        INT NOT NULL COMMENT '部署任务',
    server_id      INT NOT NULL COMMENT '服务器ID',
    ip             VARCHAR(50) NOT NULL COMMENT 'IP地址，必须冗余在这里，防止server被改动后影响现有的发布',
    server_name    VARCHAR(100) NOT NULL COMMENT '服务器名称，冗余字段',
    deploy_status  TINYINT NOT NULL DEFAULT 3 COMMENT '部署状态, 1: 成功, 2: 失败, 3: 等待部署',
    start_time     DATETIME DEFAULT NULL COMMENT '开始发布时间',
    finish_time    DATETIME DEFAULT NULL COMMENT '结束发布时间',
    PRIMARY KEY (id),
    UNIQUE KEY ukey_task_id_server_id (task_id, server_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '部署服务器';

CREATE TABLE IF NOT EXISTS t_task_server_log (
    log_id         INT NOT NULL AUTO_INCREMENT,
    task_id        INT NOT NULL COMMENT '部署任务',
    server_id      INT NOT NULL COMMENT '服务器ID',
    ip             VARCHAR(50) NOT NULL COMMENT 'IP地址，必须冗余在这里，防止server被改动后影响现有的发布',
    content        VARCHAR(255) NOT NULL COMMENT '日志内容，每行日志一条记录',
    create_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (log_id),
    KEY key_task_server_time (task_id, server_id, create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '发布某台服务器产生的日志';

CREATE TABLE IF NOT EXISTS t_task_log (
    log_id         INT NOT NULL AUTO_INCREMENT,
    task_id        INT NOT NULL COMMENT '部署任务',
    content        VARCHAR(255) NOT NULL COMMENT '日志内容，每行日志一条记录',
    create_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (log_id),
    KEY key_task_id (task_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '执行任务时的日志';
