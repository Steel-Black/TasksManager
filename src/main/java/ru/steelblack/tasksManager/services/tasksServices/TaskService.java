package ru.steelblack.tasksManager.services.tasksServices;

import ru.steelblack.tasksManager.dto.TaskDto.TaskDto;
import ru.steelblack.tasksManager.models.task.Task;

import java.util.List;

public interface TaskService {
    void addTaskToQueue(Task task);

    List<TaskDto> getAllTasks();

    void addTasksToDB();

    Task getTaskById(int id);

    void updateTask(int id, Task task);

    void appointWorker(int id, int workerId);

    List<TaskDto> getAllTasksByWorkerId(int id);
}
