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
    //데이터 소스 객체를 생성.
    //SQL을 실행할때마다 커넥션 맺고 끊는 I/O작업을 하는데 리소스가 상당하기때문에 커넥션 풀을 사용.
    //커넥션 객체를 생성해두고 DB에 접근하는 사용자에게 미리 생성해둔 커넥션을 제공했다가 돌려받는 방법.

}
