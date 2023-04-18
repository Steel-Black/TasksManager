package ru.steelblack.tasksManager.services.tasksServices;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.steelblack.tasksManager.dao.taskDao.TaskDao;
import ru.steelblack.tasksManager.dto.TaskDto.TaskDto;
import ru.steelblack.tasksManager.models.task.Status;
import ru.steelblack.tasksManager.models.task.Task;
import ru.steelblack.tasksManager.services.queueServices.QueueService;

import java.util.Date;
import java.util.List;


@Service
@Log4j2
public class TaskServiceImpl implements TaskService {

    private static final int MAX_SIZE = 11;
    private static final int COUNT_OF_TASKS = 3;

    private final TaskDao taskDao;

    private final QueueService queue;

    public TaskServiceImpl(TaskDao taskDao, QueueService queue) {
        this.taskDao = taskDao;

        this.queue = queue;
    }

    @Override
    public void addTaskToQueue(Task task) {
        task.setStatus(Status.TODO);
        task.setTime(new Date());
        queue.add(task);
            if (queue.size() >= MAX_SIZE){
                addTasksToDB();
            }
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskDao.getAllTasks();
    }

    @Override
    public void addTasksToDB() {
        if (queue.size() == 0){
            log.error("Очередь не заполнена");
        }
        for (int i = 0; i < COUNT_OF_TASKS; i++){
            Task task = queue.poll();
            if (task==null){
                break;
            }
            taskDao.addTasksToDB(task);
        }
    }

    @Override
    public Task getTaskById(int id) {
        Task task = taskDao.getTaskById(id);
        if (task == null){
            return new Task();
        }
        return taskDao.getTaskById(id);
    }

    @Override
    public void updateTask(int id, Task task) {
        taskDao.updateTask(id, task);
    }
    @Override
    public void appointWorker(int id, int workerId) {
        taskDao.appointWorker(id, workerId);
    }

    public List<TaskDto> getAllTasksByWorkerId(int id){
       return taskDao.getAllTasksByWorkerId(id);
    }

}
