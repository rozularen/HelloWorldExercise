package com.marcos.helloworldexercise.data;

import java.util.Date;

/**
 * Created by markc on 13/01/2018.
 */

public class User {
    private Integer id;
    private String name;
    private Date birthdate;

    public User(Integer id, String name, Date birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public User(String name, Date birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Date getBirthdate() {
        return birthdate;
    }
}
