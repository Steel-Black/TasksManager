package ru.steelblack.tasksManager.models.worker;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Worker {

    private long id;

    private String name;
    @JsonProperty(value = "status")
    private Position position;

    private Image avatar;

    public Image getAvatar() {
        if (avatar == null){
            avatar = new BaseImage();
        }
        return avatar;
    }


}
