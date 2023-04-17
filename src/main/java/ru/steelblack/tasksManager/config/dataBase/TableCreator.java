package ru.steelblack.tasksManager.config.dataBase;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TableCreator {

    public TableCreator(JdbcTemplate jdbcTemplate) {

        jdbcTemplate.execute("CREATE TABLE if not exists  workers(" +
                "id int generated by default as identity PRIMARY KEY, " +
                "name varchar(250) not null," +
                "position varchar(250) not null," +
                "avatar bytea)");

        jdbcTemplate.execute("CREATE TABLE if not exists tasks(" +
                "id int generated by default as identity PRIMARY KEY, " +
                "title varchar(250) not null," +
                "description varchar(1000) not null," +
                "time date not null ," +
                "status varchar(250) not null," +
                "performer int references workers(id))");
    }
}
