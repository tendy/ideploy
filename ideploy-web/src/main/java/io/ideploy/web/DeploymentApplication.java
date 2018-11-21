package io.ideploy.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 功能：main
 * 详细：
 *
 * @author linyi, 2018/11/21.
 */
@SpringBootApplication
@EnableAutoConfiguration
public class DeploymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeploymentApplication.class, args);
    }
}
