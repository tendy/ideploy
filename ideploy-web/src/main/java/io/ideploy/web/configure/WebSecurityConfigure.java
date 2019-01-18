package io.ideploy.web.configure;



import com.alibaba.fastjson.JSON;
import io.ideploy.common.constants.ApiCode;
import io.ideploy.common.entity.RestResult;
import io.ideploy.web.security.UrlAccessDecisionManager;
import io.ideploy.web.security.UrlFilterInvocationSecurityMetadataSource;
import io.ideploy.web.security.UserDetailServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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

    @Autowired
    private UrlAccessDecisionManager urlAccessDecisionManager;

    @Autowired
    private UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;

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
            .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                @Override
                public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                    o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
                    o.setAccessDecisionManager(urlAccessDecisionManager);
                    return o;
                }
            }).and().formLogin().loginPage("/login").successHandler((request, response, authentication)->{
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    RestResult result = new RestResult(ApiCode.SUCCESS, "登录成功");
                    out.println(JSON.toJSONString(result));
                    out.flush();
                    out.close();
            })
            .permitAll()
            .and()
            .logout()
            .permitAll()
            .and()
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

}
