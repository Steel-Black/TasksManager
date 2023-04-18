package ru.steelblack.tasksManager.models.worker;

import lombok.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Data
public class Image {

    private static final String IMG_DIRECTORY = "avatarImages/";

    private String name;

    private byte[] bytes;

    public Image(String fileName) {

        this.name = fileName;
        try {
            bytes = Files.readAllBytes(Paths.get(IMG_DIRECTORY.concat(name)));
            System.out.println(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
