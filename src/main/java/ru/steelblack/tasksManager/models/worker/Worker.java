package ru.steelblack.tasksManager.models.worker;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.File;

@Data
public class Worker {

    private long id;

    private String name;
    @JsonProperty(value = "status")
    private Position position;

    private File avatar;
}
