package io.ideploy.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author code4china
 * @description
 * @date Created in 00:02 2018/12/22
 */
@Data
@Configuration
@PropertySource({"application.yml"})
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidConfig {

    private int initialSize;

    private int minIdle;

    private int maxActive;

    private int maxWait;

    private long timeBetweenEvictionRunsMillis;

    private long minEvictableIdleTimeMillis;

    private String validationQuery;

    private boolean testWhileIdle;

    private boolean testOnBorrow;

    private boolean testOnReturn;

    private boolean poolPreparedStatements;

    private int maxPoolPreparedStatementPerConnectionSize;

    private String filters;

    private long timeBetweenLogStatsMillis;

    private boolean useGlobalDataSourceStat;

}
