package ru.steelblack.tasksManager.services.workersServices;

import org.springframework.stereotype.Service;
import ru.steelblack.tasksManager.dao.workerDao.WorkerDao;
import ru.steelblack.tasksManager.dto.TaskDto.TaskDto;
import ru.steelblack.tasksManager.dto.WorkerDto.WorkerDto;
import ru.steelblack.tasksManager.models.task.Task;
import ru.steelblack.tasksManager.models.worker.Worker;
import ru.steelblack.tasksManager.services.tasksServices.TaskService;

import java.util.List;


@Service
public class WorkServiceImpl implements WorkerService{

    private final WorkerDao workerDao;
    private final TaskService taskService;

    public WorkServiceImpl(WorkerDao workerDao, TaskService taskService) {
        this.workerDao = workerDao;
        this.taskService = taskService;
    }
    @Override
    public void addWorker(Worker worker){
        workerDao.addWorker(worker);
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
    public WorkerDto getWorker(int id) {
        Worker worker = workerDao.getWorker(id);
        List<TaskDto> listList = taskService.getAllTasksByWorkerId((int)worker.getId());
        return new WorkerDto(worker, listList);
    }
}
