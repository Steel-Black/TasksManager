package ru.steelblack.tasksManager.services.queueServices;

import org.springframework.stereotype.Service;
import ru.steelblack.tasksManager.models.task.Task;

import java.util.PriorityQueue;
import java.util.Queue;

@Service
public class QueueServiceImpl implements QueueService {

    private final Queue<Task> queue;

    public QueueServiceImpl() {
        this.queue = new PriorityQueue<>();
    }
    @Override
    public void add(Task task){
        queue.add(task);
    }
    @Override
    public Task poll(){
      return queue.poll();
    }
    @Override
    public int size(){
        return queue.size();
    }
}
