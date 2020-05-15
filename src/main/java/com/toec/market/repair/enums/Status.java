package com.toec.market.repair.enums;

public enum Status {
    success("index"),
    usernameOrpasswardError("401"),
    noPermission("402");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
