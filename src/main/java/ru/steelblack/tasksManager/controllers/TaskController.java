package ru.steelblack.tasksManager.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.steelblack.tasksManager.dto.TaskDto.TaskDto;
import ru.steelblack.tasksManager.dto.TaskManagerResponse;
import ru.steelblack.tasksManager.models.task.Task;
import ru.steelblack.tasksManager.services.tasksServices.TaskService;

import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {

  private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //принимающий задачу и складывающий в очередь, реализованную в сервисе, инструментами java.(без внешних MQ и т.д.)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<TaskManagerResponse> addTaskToQueue(Task task){

        return new ResponseEntity<>(taskService.addTaskToQueue(task), HttpStatus.OK);
    }
    // считывающий 3 задачи из реализованной очереди и складывающий их в БД несколькими потоками(PG или Oracle).
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public TaskManagerResponse addTasksToDB(){
        return taskService.addTasksToDB();
    }
    //выдающий все задачи из базы в списке с сокращенными данными (id, title, status).
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    //выдающий задачу по id с полным описанием.
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Task> getTaskById(@PathVariable int id){
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    //меняющий задачу по id (все кроме id и performer).
    @RequestMapping(value = "update/{id}", method = RequestMethod.PATCH)
    public TaskManagerResponse updateTaskById(@PathVariable int id, Task task){
        return taskService.updateTask(id, task);
    }
   // назначить на задачу исполнителя.
   @RequestMapping(value = "appointWorker/{id}", method = RequestMethod.PATCH)
    public TaskManagerResponse appointWorker(@PathVariable int id, int performerId){
        return taskService.appointWorker(id, performerId);
    }

}
