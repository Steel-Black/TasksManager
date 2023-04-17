package ru.steelblack.tasksManager.services.queueServices;

import ru.steelblack.tasksManager.models.Task;

public interface QueueService {
    void add(Task task);

    Task poll();

    int size();
}
