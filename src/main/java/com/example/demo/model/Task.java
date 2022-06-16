package com.example.demo.model;

import javax.persistence.*;

@Entity(name = "task")
public class TaskModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public String title;

    public enum status {
        active,
        inProgress,
        done,
        canceled,
    };

    @JoinColumn(name = "user_id")
    private UserModel owner;

//    public createdAt;
//
//    public updatedAt;

}
