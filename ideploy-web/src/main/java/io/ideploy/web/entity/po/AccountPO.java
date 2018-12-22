package io.ideploy.web.entity.po;

import java.util.Date;
import lombok.Data;

/**
 * @author code4china
 * @description
 * @date Created in 00:24 2018/12/22
 */
@Data
public class AccountPO {

    private long uid;

    private String name;

    private short type;

    private String nick;

    private String phone;

    private String email;

    private String dding;

    private Date createTime;

    private Date updateTime;

}
