package ru.steelblack.tasksManager.models;

import lombok.Data;

import java.io.File;

@Data
public class Worker {

    private long id;

    private String name;

    private int position;

    private File avatar;
}
