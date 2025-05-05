package com.utils;

public class POJO {
    private String id;
    private String name;
    private String organization;

    public POJO(String id, String name, String organization) {
        this.id = id;
        this.name = name;
        this.organization = organization;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
    @Override
    public String toString() {
        return "POJO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", organization='" + organization + '\'' +
                '}';
    }
}
