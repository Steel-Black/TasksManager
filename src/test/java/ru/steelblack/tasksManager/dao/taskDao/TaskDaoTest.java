package ru.steelblack.tasksManager.dao.taskDao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.steelblack.tasksManager.config.dataBase.TableCreator;
import ru.steelblack.tasksManager.dao.workerDao.WorkerDao;
import ru.steelblack.tasksManager.dao.workerDao.WorkerMapper;
import ru.steelblack.tasksManager.dto.TaskDto;
import ru.steelblack.tasksManager.models.worker.Position;
import ru.steelblack.tasksManager.models.task.Status;
import ru.steelblack.tasksManager.models.task.Task;
import ru.steelblack.tasksManager.models.worker.Worker;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
@SpringBootTest
class TaskDaoTest {
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private WorkerDao workerDao;

    @BeforeEach
    void setUp(){
        TableCreator tableCreator = new TableCreator(jdbcTemplate);

       Task task = newTask("titleTest", "desc");
        taskDao.addTasksToDB(task);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Worker worker = new Worker();
        worker.setName("testWorker");
        worker.setPosition(Position.SYSTEM_PROGRAMMER);
        workerDao.addWorker(worker);
    }

    private Task newTask (String title, String desc){
        Task newTask = new Task();
        newTask.setTitle(title);
        newTask.setDescription(desc);
        newTask.setTime(new Date());
        newTask.setStatus(Status.TODO);

        return newTask;
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.update("delete from tasks");
        jdbcTemplate.update("delete from workers");
    }

    @Test
    public void getAllTasks() {
        int expectedCount = jdbcTemplate.query("select * from tasks", new TaskMapper(workerDao)).size();

        //when
        List<TaskDto> tasks = taskDao.getAllTasks();
        int actualCount = tasks.size();
        //then
        assertEquals(expectedCount, actualCount);
    }
    @Test
    public void getTaskById() {
        long id = jdbcTemplate.query("select * from tasks", new TaskMapper(workerDao)).get(0).getId();
        //when
        Task task = taskDao.getTaskById((int) id);
        //then
        assertEquals(id, task.getId());
    }
    @Test
    public void updateTask() {
       Task task = jdbcTemplate.query("select * from tasks", new TaskMapper(workerDao)).get(0);
       Task updatedTask = newTask("new Title", "new Desc");
        //when
       taskDao.updateTask((int)task.getId(), updatedTask);
        //then
       Task taskAfterUpdate = jdbcTemplate.query("select * from tasks", new TaskMapper(workerDao)).get(0);
       assertNotEquals(task.toString(), taskAfterUpdate.toString());
    }
    @Test
    public void addTasksToDB(){
        Task task = newTask("newTask", "new Desc");
        //when
        taskDao.addTasksToDB(task);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //then
        List<Task> list = jdbcTemplate.query("select * from tasks where title=?", new Object[]{task.getTitle()},new TaskMapper(workerDao));

        assertEquals(1, list.size());
    }

    @Test
    void appointWorker() {

        Task task = jdbcTemplate.query("select * from tasks where title=?", new Object[]{"titleTest"} ,new TaskMapper(workerDao)).get(0);
        long id = jdbcTemplate.query("select * from workers",new WorkerMapper()).get(0).getId();
        //when
        taskDao.appointWorker((int)task.getId(), (int) id);
        //then
        Task taskAfterAppoint = jdbcTemplate.query("select * from tasks where id=?",new Object[]{task.getId()}, new TaskMapper(workerDao)).get(0);

        assertNotEquals(task, taskAfterAppoint);
    }
}