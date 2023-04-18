package ru.steelblack.tasksManager.dto;

import lombok.Getter;
import lombok.Setter;
import ru.steelblack.tasksManager.models.task.Status;

@Getter
@Setter
public class TaskDto {

    private long id;

    private String title;

    private Status status;
}
