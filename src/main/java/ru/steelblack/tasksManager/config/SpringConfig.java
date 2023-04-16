package ru.steelblack.tasksManager.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.steelblack.tasksManager.config.dataBase.DataBaseProperties;

import javax.sql.DataSource;


@Configuration
@ComponentScan("ru.steelblack.tasksManager")
public class SpringConfig {
    private final DataBaseProperties dataBaseProperties;

    public SpringConfig(DataBaseProperties dataBaseProperties) {
        this.dataBaseProperties = dataBaseProperties;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(dataBaseProperties.getDataSource().get("url"));
        dataSource.setUsername(dataBaseProperties.getDataSource().get("username"));
        dataSource.setPassword(dataBaseProperties.getDataSource().get("password"));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

}
