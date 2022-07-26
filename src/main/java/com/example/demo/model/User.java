package com.example.demo.model;
import java.util.List;
import javax.persistence.*;

@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 10)
    public String name;

    @Column(nullable = false, length = 15)
    public String surname;

    @Column(nullable = false, length = 25)
    public String username;

    @Column(nullable = false, length = 240)
    public String password;

    @OneToMany(mappedBy = "owner")
    private List<Task> tasks;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws Exception {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
