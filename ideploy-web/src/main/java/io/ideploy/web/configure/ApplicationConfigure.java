package io.ideploy.web.configure;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: code4china
 * @description:
 * @date: Created in 14:48 2018/12/21
 */
@Configuration
@MapperScan("io.ideploy.web.dao")
public class ApplicationConfigure {

}
