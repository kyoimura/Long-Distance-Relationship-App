package com.kyo.vehicleApp.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class FuturePlans {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;



    @Column(name = "username") // Make sure the column name matches your database schema
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Column(name = "futureBody") //description to postBody
    private String futureBody;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }




    public String getFutureBody() {
        return futureBody;
    }

    public void setFutureBody(String futureBody) {
        this.futureBody = futureBody;
    }

}
