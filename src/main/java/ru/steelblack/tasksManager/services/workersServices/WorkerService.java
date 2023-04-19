package ru.steelblack.tasksManager.services.workersServices;

import ru.steelblack.tasksManager.dto.TaskManagerResponse;
import ru.steelblack.tasksManager.dto.WorkerDto.WorkerDto;
import ru.steelblack.tasksManager.models.worker.Worker;

public interface WorkerService {

   TaskManagerResponse addWorker(Worker worker);

   TaskManagerResponse updateWorker(Worker worker, int id);

   WorkerDto getWorker(int id);

   TaskManagerResponse deleteWorker(int id);
}
