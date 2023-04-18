package ru.steelblack.tasksManager.services.queueServices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.steelblack.tasksManager.models.task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
@SpringBootTest
class QueueServiceImplTest {
    @Autowired
    private QueueService queueService;


    @Test
    void add() {
        Task task = new Task();
        int expectedSize = queueService.size() + 1;
        queueService.add(task);
        int actualSize = queueService.size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void poll() {
        Task expectedTask = new Task();
        queueService.add(expectedTask);
        Task actualTask = queueService.poll();
        assertEquals(expectedTask, actualTask);
    }

    @Test
    void size() {
        Task task = new Task();
        int expectedSize = queueService.size();
        queueService.add(task);
        int actualSize = queueService.size();
        assertNotEquals(expectedSize, actualSize);
    }
}