package ru.steelblack.tasksManager.services.tasksServices;

import ru.steelblack.tasksManager.dto.TaskDto.TaskDto;
import ru.steelblack.tasksManager.dto.TaskManagerResponse;
import ru.steelblack.tasksManager.models.task.Task;

import java.util.List;

public interface TaskService {
    TaskManagerResponse addTaskToQueue(Task task);

    List<TaskDto> getAllTasks();

    TaskManagerResponse addTasksToDB();

    Task getTaskById(int id);

    TaskManagerResponse updateTask(int id, Task task);

    TaskManagerResponse appointWorker(int id, int performerId);

    List<TaskDto> getAllTasksByWorkerId(int id);
}
