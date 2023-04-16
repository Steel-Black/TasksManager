package ru.steelblack.tasksManager.services.workerService;

import ru.steelblack.tasksManager.models.Worker;

public interface WorkerService {

   void createWorker(Worker worker);

   void updateWorker(Worker worker, int id);

   Worker getWorker(int id);

   void deleteWorker(int id);
}
