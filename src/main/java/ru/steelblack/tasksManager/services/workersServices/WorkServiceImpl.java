package ru.steelblack.tasksManager.services.workersServices;

import org.springframework.stereotype.Service;
import ru.steelblack.tasksManager.dao.workerDao.WorkerDao;
import ru.steelblack.tasksManager.models.Worker;

@Service
public class WorkServiceImpl implements WorkerService{

    private final WorkerDao workerDao;

    public WorkServiceImpl(WorkerDao workerDao) {
        this.workerDao = workerDao;
    }
    @Override
    public void createWorker(Worker worker){
        workerDao.createWorker(worker);
    }
    @Override
    public void updateWorker(Worker worker, int id){
        workerDao.updateWorker(worker, id);
    }
    @Override
    public void deleteWorker(int id){
        workerDao.deleteWorker(id);
    }
    @Override
    public Worker getWorker(int id){
       return workerDao.getWorker(id);
    }
}
