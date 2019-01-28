package io.ideploy.web.service.account;

import io.ideploy.common.util.EntityUtil;
import io.ideploy.web.dao.account.AccountDao;
import io.ideploy.web.entity.po.AccountPO;
import io.ideploy.web.entity.vo.Account;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author code4china
 * @description
 * @date Created in 00:20 2018/12/25
 */
@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    public Account getByAccountId(String accountId){
        AccountPO po = accountDao.getByAccountId(accountId);
        return EntityUtil.from(po, Account.class);
    }
}
