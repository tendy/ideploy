package io.ideploy.web.service.account;

import io.ideploy.web.entity.vo.Account;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author code4china
 * @description
 * @date Created in 00:20 2018/12/25
 */
@Service
public class AccountService {

    private static Map<String, Account> testData = new HashMap<>();
    static {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Account account = new Account();
        account.setUid(1);
        account.setNick("管理员");
        account.setAccountId("root");
        account.setPassword(encoder.encode("root"));
        account.setEmail("root@ideploy.io");
        testData.put("root", account);
    }

    public Account getByAccountId(String accountId){
        return testData.get(accountId);
    }
}
