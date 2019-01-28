package io.ideploy.web.dao.account;

import io.ideploy.web.entity.po.AccountPO;

/**
 * @author code4china
 * @description
 * @date Created in 17:48 2018/12/21
 */
public interface AccountDao {

    AccountPO getByAccountId(String accountId);

}
