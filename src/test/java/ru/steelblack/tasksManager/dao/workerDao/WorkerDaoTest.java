package ru.steelblack.tasksManager.dao.workerDao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.steelblack.tasksManager.models.worker.BaseImage;
import ru.steelblack.tasksManager.models.worker.Position;
import ru.steelblack.tasksManager.models.worker.Worker;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class WorkerDaoTest {
    @Autowired
    private WorkerDao workerDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        Worker worker = newWorker("TestWorker", Position.TESTER);
        workerDao.addWorker(worker);
    }
    private Worker newWorker(String name, Position position){
        Worker worker = new Worker();
        worker.setName(name);
        worker.setPosition(position);
        worker.setAvatar(new BaseImage());
        return worker;
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.update("delete from workers");
    }

    @Test
    void addWorker() {
        Worker worker = newWorker("nick", Position.SYSTEM_PROGRAMMER);
        //when
        workerDao.addWorker(worker);
        //then
        List<Worker> list = jdbcTemplate.query("select * from workers where name=?", new Object[]{worker.getName()}, new WorkerMapper());
        assertEquals(1, list.size());
    }

    @Test
    void updateWorker() {
        Worker worker = jdbcTemplate.query("select * from workers", new WorkerMapper()).get(0);
        Worker updatedWorker = newWorker("UpdatedWorker", Position.MANAGER);
        //when
        workerDao.updateWorker(updatedWorker,(int)worker.getId());
        //then
        Worker workerAfterUpdate = jdbcTemplate.query("select * from workers", new WorkerMapper()).get(0);
        assertNotEquals(worker, workerAfterUpdate);
    }

    @Test
    void getWorker() {
        Worker worker = jdbcTemplate.query("select * from workers where name=?",new Object[]{"TestWorker"}, new WorkerMapper()).get(0);
        //when
        Worker actualWorker = workerDao.getWorker((int) worker.getId());
        //then
        assertEquals(worker, actualWorker);
    }

    @Test
    void deleteWorker() {
        Worker worker = jdbcTemplate.query("select * from workers where name=?",new Object[]{"TestWorker"}, new WorkerMapper()).get(0);
        //when
        workerDao.deleteWorker((int)worker.getId());
        //then
        boolean actual = jdbcTemplate.query("select * from workers where id=?",new Object[]{worker.getId()}, new WorkerMapper()).isEmpty();
        assertTrue(actual);
    }

}