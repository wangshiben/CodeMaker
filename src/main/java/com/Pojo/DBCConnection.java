package com.Pojo;

import lombok.Data;

@Data
public class DBCConnection {
    private String user;
    private String password;

    public DBCConnection() {
    }

    public DBCConnection(String user, String password) {
        this.user = user;
        this.password = password;
    }
}
