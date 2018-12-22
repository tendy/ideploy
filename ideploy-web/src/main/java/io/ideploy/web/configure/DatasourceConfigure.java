package io.ideploy.web.configure;

import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * @author code4china
 * @description
 * @date Created in 17:29 2018/12/21
 */
@Configuration
public class DatasourceConfigure {

    @Value("${spring.datasource.driver-class-name}")
    private String jdbcDriver;

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String jdbcUser;

    @Value("${spring.datasource.password}")
    private String jdbcPassword;

    @Autowired
    private DruidConfig druidConfig;

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource,  ResourcePatternResolver resolver) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
        sqlSessionFactory.setMapperLocations(resolver.getResources("classpath:/mybatis/**/*.xml"));
        return sqlSessionFactory;
    }

    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(jdbcDriver);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUser);
        dataSource.setPassword(jdbcPassword);

        dataSource.setInitialSize(druidConfig.getInitialSize());
        dataSource.setMinIdle(druidConfig.getMinIdle());
        dataSource.setMaxActive(druidConfig.getMaxActive());
        dataSource.setMaxWait(druidConfig.getMaxWait());
        dataSource.setTimeBetweenEvictionRunsMillis(druidConfig.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(druidConfig.getMinEvictableIdleTimeMillis());
        dataSource.setValidationQuery(druidConfig.getValidationQuery());
        dataSource.setTestWhileIdle(druidConfig.isTestWhileIdle());
        dataSource.setTestOnBorrow(druidConfig.isTestOnBorrow());
        dataSource.setTestOnReturn(druidConfig.isTestOnReturn());
        dataSource.setPoolPreparedStatements(druidConfig.isPoolPreparedStatements());
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(druidConfig.getMaxPoolPreparedStatementPerConnectionSize());
        dataSource.setUseGlobalDataSourceStat(druidConfig.isUseGlobalDataSourceStat());
        return dataSource;
    }


}
