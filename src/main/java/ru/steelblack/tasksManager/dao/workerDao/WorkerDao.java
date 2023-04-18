package ru.steelblack.tasksManager.dao.workerDao;

import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.steelblack.tasksManager.models.worker.Worker;

import java.util.Optional;

@Component
@Log4j2
public class WorkerDao {

    private final JdbcTemplate jdbcTemplate;

    private final WorkerMapper mapper;

    public WorkerDao(JdbcTemplate jdbcTemplate, WorkerMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    public void addWorker(Worker worker) {
        saveWorker(worker);
    }

    public void updateWorker(Worker updatedWorker, int id) {
        jdbcTemplate.update("update workers set name=?, position=?, avatar=? where id=?",
                updatedWorker.getName(),
                updatedWorker.getPosition().toString(),
                updatedWorker.getAvatar().getName(),
                id);
    }

    public Worker getWorker(int id) {
        Worker worker = null;
        Optional<Worker> workerOptional = jdbcTemplate.query("select * from workers where id =?",
                new Object[]{id},
                mapper).stream().findAny();
        System.out.println(workerOptional.isEmpty());

        if (workerOptional.isPresent()) {
            worker = workerOptional.get();
        }
        else {
            log.error("Worker not found");
        }
        return worker;
    }

    public void deleteWorker(int id) {
        jdbcTemplate.update("delete from workers where id=?", id);
    }

    private void saveWorker(Worker worker){

        jdbcTemplate.update("insert into workers(name, position, avatar) values (?,?,?)",
                worker.getName(),
                worker.getPosition().toString(),
                worker.getAvatar().getName());
    }
}
