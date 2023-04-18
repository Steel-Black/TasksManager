package ru.steelblack.tasksManager.models.task;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.steelblack.tasksManager.models.worker.Worker;

import java.util.Date;

@Data
@NoArgsConstructor
public class Task implements Comparable<Task> {

    private long id;

    private String title;

    private String description;

    private Date time;

    @JsonProperty(value = "status")
    private Status status;

    private Worker performer;

    @Override
    public int compareTo(Task o) {
        if (this.hashCode() > o.hashCode()){
            return 1;
        }
        if (this.hashCode() < o.hashCode()){
            return -1;
        }
        return 0;
    }
}
