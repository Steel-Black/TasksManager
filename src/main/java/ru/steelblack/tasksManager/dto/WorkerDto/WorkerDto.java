package ru.steelblack.tasksManager.dto.WorkerDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.steelblack.tasksManager.dto.TaskDto.TaskDto;
import ru.steelblack.tasksManager.models.worker.Worker;

import java.util.List;
@Data
@AllArgsConstructor
public class WorkerDto {
    private Worker worker;

    private List<TaskDto> toDoList;
}
