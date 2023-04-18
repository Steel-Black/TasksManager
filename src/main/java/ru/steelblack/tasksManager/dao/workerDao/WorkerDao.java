package ru.steelblack.tasksManager.dao.workerDao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.steelblack.tasksManager.models.worker.Worker;

@Component
public class WorkerDao {

    private final JdbcTemplate jdbcTemplate;

    public WorkerDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addWorker(Worker worker) {
        saveWorker(worker);
    }

    public void updateWorker(Worker updatedWorker, int id) {
        jdbcTemplate.update("update workers set name=?, position=?, avatar=? where id=?",
                updatedWorker.getName(),
                updatedWorker.getPosition().toString(),
                updatedWorker.getAvatar(),
                id);
    }

    public Worker getWorker(int id) {
        return jdbcTemplate.query("select * from workers where id =?",
                new Object[]{id},
                new WorkerMapper()).stream().findAny().orElse(null);
    }

    public void deleteWorker(int id) {
        jdbcTemplate.update("delete from workers where id=?", id);
    }

    private void saveWorker(Worker worker){
        jdbcTemplate.update("insert into workers(name, position, avatar) values (?,?,?)",
                worker.getName(),
                worker.getPosition().toString(),
                worker.getAvatar());
    }
}
