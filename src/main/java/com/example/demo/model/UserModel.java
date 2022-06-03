package com.example.demo.model;

import javax.persistence.*;

@Entity(name = "user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false, length = 10)
    public String name;

    @Column(nullable = false, length = 15)
    public String surname;

    @Column(nullable = false, length = 25)
    public String username;

    @Column(nullable = false, length = 15)
    public String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
