package com.example.demo.model;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.*;
import com.example.demo.model.TaskModel;

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

    @OneToMany(mappedBy = "owner")
    private List<TaskModel> tasks;

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

    public void setPassword(String password) throws Exception {
        String salt = "TYHbSD1!@%";
        this.password = salt + this.getHashMd5(password);
    }

    public static String getHashMd5(String value) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        BigInteger hash = new BigInteger(1, md.digest(value.getBytes()));
        return hash.toString(16);
    }

}
