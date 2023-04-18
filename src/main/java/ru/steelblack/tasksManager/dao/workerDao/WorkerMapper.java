package ru.steelblack.tasksManager.dao.workerDao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.steelblack.tasksManager.models.worker.Image;
import ru.steelblack.tasksManager.models.worker.Position;
import ru.steelblack.tasksManager.models.worker.Worker;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class WorkerMapper implements RowMapper<Worker> {
    @Override
    public Worker mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Worker worker = new Worker();

        worker.setId(resultSet.getInt("id"));
        worker.setName(resultSet.getString("name"));
        worker.setPosition(Position.valueOf(resultSet.getString("position")));

        Image image = new Image(resultSet.getString("avatar"));
        worker.setAvatar(image);

        return worker;

    }
}
