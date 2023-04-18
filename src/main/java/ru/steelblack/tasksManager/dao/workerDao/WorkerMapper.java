package ru.steelblack.tasksManager.dao.workerDao;

import org.springframework.jdbc.core.RowMapper;
import ru.steelblack.tasksManager.models.worker.Position;
import ru.steelblack.tasksManager.models.worker.Worker;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;


public class WorkerMapper implements RowMapper<Worker> {
    @Override
    public Worker mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Worker worker = new Worker();

        worker.setId(resultSet.getInt("id"));
        worker.setName(resultSet.getString("name"));
        worker.setPosition(Position.valueOf(resultSet.getString("position")));
        worker.setAvatar((File)resultSet.getObject("avatar"));

        return worker;

    }
}
