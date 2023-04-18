package ru.steelblack.tasksManager.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.steelblack.tasksManager.dto.TaskDto.TaskDto;
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
    public ResponseEntity<HttpStatus> addTaskToQueue(Task task){
        taskService.addTaskToQueue(task);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    // считывающий 3 задачи из реализованной очереди и складывающий их в БД несколькими потоками(PG или Oracle).
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public void addTasksToDB(){
        taskService.addTasksToDB();
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
    public ResponseEntity<HttpStatus> updateTaskById(@PathVariable int id, Task task){
        taskService.updateTask(id, task);
        return ResponseEntity.ok(HttpStatus.OK);
    }
   // назначить на задачу исполнителя.
   @RequestMapping(value = "appointWorker/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<HttpStatus> appointWorker(@PathVariable int id, int workerId){
       taskService.appointWorker(id, workerId);
       return ResponseEntity.ok(HttpStatus.OK);
    }

}
