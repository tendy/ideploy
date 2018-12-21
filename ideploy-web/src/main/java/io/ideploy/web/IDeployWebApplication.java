package io.ideploy.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author code4china
 * @description 服务启动入口
 * @date Created in 14:46 2018/12/21
 */
@EnableAutoConfiguration
@SpringBootApplication
public class IDeployWebApplication {

    public static void main(String[]args){
        SpringApplication.run(IDeployWebApplication.class, args);
    }

}
