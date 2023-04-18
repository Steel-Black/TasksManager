package ru.steelblack.tasksManager.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.steelblack.tasksManager.models.worker.Worker;
import ru.steelblack.tasksManager.services.workersServices.WorkerService;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void addWorker(Worker worker) {
        workerService.addWorker(worker);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH)
    public void updateWorker(Worker worker, @PathVariable("id") int id) {
        workerService.updateWorker(worker, id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteWorker(@PathVariable("id")int id) {
        workerService.deleteWorker(id);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Worker> getWorker(@PathVariable("id")int id) {

        return new ResponseEntity<>(workerService.getWorker(id), HttpStatus.OK);
    }

}
