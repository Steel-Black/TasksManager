package ru.steelblack.tasksManager.services.tasksServices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.steelblack.tasksManager.config.dataBase.TableCreator;
import ru.steelblack.tasksManager.dao.taskDao.TaskDao;
import ru.steelblack.tasksManager.dao.taskDao.TaskMapper;
import ru.steelblack.tasksManager.models.task.Status;
import ru.steelblack.tasksManager.models.task.Task;
import ru.steelblack.tasksManager.services.queueServices.QueueService;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskDao taskDao;
    @Mock
    private QueueService queueService;

    private TaskService taskService;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @Mock
    private TaskMapper mapper;

    @BeforeEach
    void setUp() {
        TableCreator tableCreator = new TableCreator(jdbcTemplate);
        taskService = new TaskServiceImpl(taskDao, queueService);
    }

    @Test
    void addTaskToQueueTestMethod() {
        //given
        Task task = new Task();
        //when
        taskService.addTaskToQueue(task);
        //then
        verify(queueService).add(task);

    }

    @Test
    void addTaskToQueueTestParam() {
        //given
        Task task = new Task();
        //when
        taskService.addTaskToQueue(task);
        //then
        ArgumentCaptor<Task> taskArgumentCaptor = ArgumentCaptor.forClass(Task.class);

        verify(queueService).add(taskArgumentCaptor.capture());

        Task capturedTask = taskArgumentCaptor.getValue();

        assertThat(capturedTask).isEqualTo(task);
    }

    @Test
    void getAllTasksTestMethod() {
        //when
        taskService.getAllTasks();
        //then
        verify(taskDao).getAllTasks();
    }

    @Test
    @Disabled
    void addTasksToDB() {
        //given
        Task task = new Task();
        task.setTitle("title");
        task.setDescription("description");
        task.setStatus(Status.TODO);
        task.setTime(new Date());
        verify(queueService).add(task);
        int i = queueService.size();
        //when
        verify(taskService).addTasksToDB();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Task> list = verify(jdbcTemplate).query("select * from tasks where title=? and description=?", new Object[]{task.getTitle(), task.getDescription()}, mapper);

        Task taskFromDb = list.get(0);
        task.setId(taskFromDb.getId());
        task.setTime(taskFromDb.getTime());

        assertThat(task).isEqualTo(taskFromDb);

    }

    @Test
    void getTaskById() {
        int id = 1;
        //when
        taskService.getTaskById(id);
        //then
        verify(taskDao).getTaskById(id);

    }

    @Test
    void updateTaskTestParam() {
        int id = 1;
        //given
        Task task = new Task();
        //when
        taskService.updateTask(id, task);
        //then
        ArgumentCaptor<Task> taskArgumentCaptor = ArgumentCaptor.forClass(Task.class);

        verify(taskDao).updateTask(eq(id) , taskArgumentCaptor.capture());

        Task capturedWorker = taskArgumentCaptor.getValue();

        assertThat(capturedWorker).isEqualTo(task);
    }

    @Test
    void updateTaskTestMethod() {
        int id = 1;
        //given
        Task task = new Task();
        //when
        taskService.updateTask(id, task);
        //then
        verify(taskDao).updateTask(id, task);
    }

    @Test
    void appointWorker() {
        int taskId = 1;

        int workerId = 1;
        //given
        Task task = new Task();
        //when
        taskService.appointWorker(taskId, workerId);
        //then
        verify(taskDao).appointWorker(eq(taskId) , eq(workerId));
    }
}