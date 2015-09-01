package com.shestakam.topic.entity;

/**
 * Created by shestakam on 1.9.15.
 */
public class Topic {
    private Long id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
