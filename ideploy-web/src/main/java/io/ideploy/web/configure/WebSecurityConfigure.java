package io.ideploy.web.configure;

import static io.ideploy.web.enums.IDeployAuthrority.*;

import io.ideploy.web.service.security.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author code4china
 * @description
 * @date Created in 23:42 2018/12/23
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    /***
     * 身份验证配置，用于注入自定义身份验证Bean和密码校验规则
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /***
     * Web层面的配置，一般用来配置无需安全检查的路径
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/vendors/**", "/img/**", "/**/favicon.ico");
    }

    /***
     * Request层面的配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login.html", "/about.html", "/error/**", "logout.html").permitAll()
                .antMatchers("/account/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/project/**").hasAnyRole("PROJECT","ADMIN")
                .antMatchers("/task/**").hasAnyRole("DEPLOY","ADMIN")
                .antMatchers("/task/audit/**").hasAnyRole("DEPLOY","ADMIN")
                .antMatchers("/statis/**").hasAnyRole("STAT","ADMIN")
                .antMatchers("/setting/**").hasAnyRole("SETTING","ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

}
