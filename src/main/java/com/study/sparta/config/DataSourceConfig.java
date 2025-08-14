package com.study.sparta.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {

    @Bean(name = "metaDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.meta")
    public DataSource metaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "s1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.shard1")
    public DataSource s1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "s1Jdbc")
    public JdbcTemplate s1JdbcTemplate(@Qualifier("s1DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "s2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.shard2")
    public DataSource s2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "s2Jdbc")
    public JdbcTemplate s2JdbcTemplate(@Qualifier("s2DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "s3DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.shard3")
    public DataSource s3DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "s3Jdbc")
    public JdbcTemplate s3JdbcTemplate(@Qualifier("s3DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "s4DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.shard4")
    public DataSource s4DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "s4Jdbc")
    public JdbcTemplate s4JdbcTemplate(@Qualifier("s4DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}