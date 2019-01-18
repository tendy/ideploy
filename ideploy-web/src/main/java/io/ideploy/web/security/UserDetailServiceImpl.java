package io.ideploy.web.security;

import io.ideploy.web.entity.vo.Account;
import io.ideploy.web.entity.vo.AuthorityUser;
import io.ideploy.web.service.account.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author code4china
 * @description
 * @date Created in 10:16 2018/12/24
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private AccountService accountService;


    @Override
    public UserDetails loadUserByUsername(String accountId) throws UsernameNotFoundException {
        if (StringUtils.isBlank(accountId)) {
            throw new UsernameNotFoundException("用户ID为空");
        }
        Account account = accountService.getByAccountId(accountId);
        if(account == null){
         throw new UsernameNotFoundException("用户不存在");
        }
        AuthorityUser authorityUser = new AuthorityUser(account);
        return authorityUser;
    }
}
