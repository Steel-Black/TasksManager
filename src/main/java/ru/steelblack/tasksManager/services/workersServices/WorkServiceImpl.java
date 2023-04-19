package ru.steelblack.tasksManager.services.workersServices;

import org.springframework.stereotype.Service;
import ru.steelblack.tasksManager.dao.workerDao.WorkerDao;
import ru.steelblack.tasksManager.dto.TaskDto.TaskDto;
import ru.steelblack.tasksManager.dto.TaskManagerResponse;
import ru.steelblack.tasksManager.dto.TaskManagerResponseFailed;
import ru.steelblack.tasksManager.dto.WorkerDto.WorkerDto;
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
    public TaskManagerResponse addWorker(Worker worker){
        workerDao.addWorker(worker);
        return new TaskManagerResponse(true);
    }
    @Override
    public TaskManagerResponse updateWorker(Worker worker, int id){

        if(workerDao.updateWorker(worker, id)){
            return new TaskManagerResponse(true);
        }
        return new TaskManagerResponseFailed(false, "worker with this id does not exist!");
    }
    @Override
    public TaskManagerResponse deleteWorker(int id){
       if (workerDao.deleteWorker(id)){
           return new TaskManagerResponse(true);
       }
       return new TaskManagerResponseFailed(false, "worker with this id does not exist!");
    }
    @Override
    public WorkerDto getWorker(int id) {

        Worker worker = workerDao.getWorker(id);
        if (worker == null){
            worker = new Worker();
        }

        List<TaskDto> listList = taskService.getAllTasksByWorkerId((int)worker.getId());

        return new WorkerDto(worker, listList);
    }
}
