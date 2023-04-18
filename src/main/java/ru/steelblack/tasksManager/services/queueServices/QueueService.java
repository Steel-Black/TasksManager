package ru.steelblack.tasksManager.services.queueServices;

import ru.steelblack.tasksManager.models.task.Task;

public interface QueueService {
    void add(Task task);

    Task poll();

    int size();
}
