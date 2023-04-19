package ru.steelblack.tasksManager.services.tasksServices;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.steelblack.tasksManager.dao.taskDao.TaskDao;
import ru.steelblack.tasksManager.dto.TaskDto.TaskDto;
import ru.steelblack.tasksManager.dto.TaskManagerResponse;
import ru.steelblack.tasksManager.dto.TaskManagerResponseFailed;
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
    public TaskManagerResponse addTaskToQueue(Task task) {
        task.setStatus(Status.TODO);
        task.setTime(new Date());
        queue.add(task);
            if (queue.size() >= MAX_SIZE){
                addTasksToDB();
            }
        return new TaskManagerResponse(true);
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskDao.getAllTasks();
    }

    @Override
    public TaskManagerResponse addTasksToDB() {
        if (queue.size() == 0){
            log.error("Очередь не заполнена");
            return new TaskManagerResponseFailed(false, "Очередь не заполнена");
        }
        for (int i = 0; i < COUNT_OF_TASKS; i++){
            Task task = queue.poll();
            if (task==null){
                break;
            }
            taskDao.addTasksToDB(task);
        }
        return new TaskManagerResponse(true);
    }

    @Override
    public Task getTaskById(int id) {
        Task task = taskDao.getTaskById(id);
        if (task == null){
            task = new Task();
            log.error("task not found");
        }
        return task;
    }

    @Override
    public TaskManagerResponse updateTask(int id, Task task) {

        if(taskDao.updateTask(id, task)){
            return new TaskManagerResponse(true);
        }
        return new TaskManagerResponseFailed(false, "task with this id does not exist!");
    }
    @Override
    public TaskManagerResponse appointWorker(int id, int performerId) {

        if (taskDao.appointWorker(id, performerId)){
            return new TaskManagerResponse(true);
        }
        return new TaskManagerResponseFailed(false, "task or worker with this id does not exist!");
    }

    public List<TaskDto> getAllTasksByWorkerId(int id){
       return taskDao.getAllTasksByWorkerId(id);
    }

}
