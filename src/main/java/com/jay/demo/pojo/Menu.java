package com.jay.demo.pojo;

public class Menu {
    private Integer id;

    private String name;

    private String roles;

    private String index;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles == null ? null : roles.trim();
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index == null ? null : index.trim();
    }
}