package ru.steelblack.tasksManager.services.workersServices;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.steelblack.tasksManager.dao.workerDao.WorkerDao;
import ru.steelblack.tasksManager.models.worker.Worker;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class WorkServiceTest {

    private WorkerService workerService;
    @Mock
    private WorkerDao workerDao;

    @BeforeEach
    void setUp() {
          workerService = new WorkServiceImpl(workerDao);
    }

    @Test
    void addWorkerTestParam() {
        //given
        Worker worker = new Worker();
        //when
        workerService.addWorker(worker);
        //then
        ArgumentCaptor<Worker> workerArgumentCaptor = ArgumentCaptor.forClass(Worker.class);

        verify(workerDao).addWorker(workerArgumentCaptor.capture());

        Worker capturedWorker = workerArgumentCaptor.getValue();

        assertThat(capturedWorker).isEqualTo(worker);
    }
    @Test
    void addWorker() {
        //given
        Worker worker = new Worker();
        //when
        workerService.addWorker(worker);
        //then
        verify(workerDao).addWorker(worker);
    }

    @Test
    void updateWorkerTestParam() {
        int id = 1;
        //given
        Worker worker = new Worker();
        //when
        workerService.updateWorker(worker, id);
        //then
        ArgumentCaptor<Worker> workerArgumentCaptor = ArgumentCaptor.forClass(Worker.class);

        verify(workerDao).updateWorker(workerArgumentCaptor.capture(), eq(id));

        Worker capturedWorker = workerArgumentCaptor.getValue();

        assertThat(capturedWorker).isEqualTo(worker);
    }

    @Test
    void updateWorker() {
        int id = 1;
        //given
        Worker worker = new Worker();
        //when
        workerService.updateWorker(worker, id);
        //then
        verify(workerDao).updateWorker(worker, id);
    }

    @Test
    void deleteWorkerTestParam() {
        //given
        int id = 1;
        //when
        workerService.deleteWorker(id);
        //then
        ArgumentCaptor<Integer> workerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);

        verify(workerDao).deleteWorker(workerArgumentCaptor.capture());

        int capturedWorker = workerArgumentCaptor.getValue();

        assertThat(capturedWorker).isEqualTo(id);
    }

    @Test
    void deleteWorker() {
        //given
        int id = 1;
        //when
        workerService.deleteWorker(id);
        //then
        verify(workerDao).deleteWorker(id);
    }

    @Test
    void getWorker() {
        int id = 1;
        //when
        workerService.getWorker(id);
        //then
        verify(workerDao).getWorker(id);
    }
}