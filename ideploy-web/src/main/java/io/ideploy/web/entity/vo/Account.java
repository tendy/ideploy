package io.ideploy.web.entity.vo;

import java.util.Date;
import lombok.Data;

/**
 * @author code4china
 * @description
 * @date Created in 00:29 2018/12/22
 */
@Data
public class Account {

    private long uid;

    private String accountId;

    private String password;

    private short type;

    private String nick;

    private String phone;

    private String email;

    private String dding;

    private Date createTime;

    private Date updateTime;

}
