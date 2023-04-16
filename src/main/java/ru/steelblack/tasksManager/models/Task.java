package ru.steelblack.tasksManager.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Comparator;
import java.util.Date;

@Data
@NoArgsConstructor
public class Task implements Comparable<Task> {

    private long id;

    private String title;

    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
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
