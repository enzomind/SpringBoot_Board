package com.study.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DatabaseConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    // @PropertySource에 지정된 application.properties에서
    // prefix에 해당하는 spring.datasource.hikari로 시작하는 설정을 모두 읽어들여 해당 메서드에 매핑
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    } //DB에 빠르게 접속하기위한 커넥션 풀? 인데 그 부분의 설정

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

}
