
use `ideploy`;

SET NAMES utf8mb4;

-- 说明：
-- (1) 除了 bool 字段，一般字段不要取值 0
-- (2) status类的字段，统一用 1 表示成功

-- 'admin' 使用BCryptPasswordEncoder加密后字符串为： $2a$10$TQgZtw2BXgO8lfXV.D92JuKPoRKwSwm6d8p0Kvr5ij8kA8GiHfxoq
INSERT INTO `t_account`(uid, account_id, password,  `type`,  nick,    phone,           email,             dding,  create_time, update_time, creator, status)
                 VALUES(1,   'admin',   '$2a$10$TQgZtw2BXgO8lfXV.D92JuKPoRKwSwm6d8p0Kvr5ij8kA8GiHfxoq',     1,     '管理员',  '13800138000',  'admin@ideploy.io',  '',    now(),       now(),       1,       1);