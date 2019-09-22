package com.example.travelmo;

import java.util.Map;

public class Kandy {
    String name;
    String description;
    String img;

    public Kandy(String user, Map<String, Object> score) {
    }

    public Kandy(String name, String description, String img) {
        this.name = name;
        this.description = description;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
