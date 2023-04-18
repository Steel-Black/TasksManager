package ru.steelblack.tasksManager.services.workersServices;

import ru.steelblack.tasksManager.models.worker.Worker;

public interface WorkerService {

   void addWorker(Worker worker);

   void updateWorker(Worker worker, int id);

   Worker getWorker(int id);

   void deleteWorker(int id);
}
