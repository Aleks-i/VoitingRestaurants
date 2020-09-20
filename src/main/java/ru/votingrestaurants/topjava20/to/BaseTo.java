package ru.votingrestaurants.topjava20.to;


import ru.votingrestaurants.topjava20.HasId;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public abstract class BaseTo implements HasId {
    protected Integer id;

    @Size(min = 5, max = 30)
    @Column(name = "name", nullable = false)
    protected String name;

    @Size(min = 4, max = 20)
    @Column(name = "email", nullable = false)
    protected String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 3, max = 21)
    protected String password;

    public BaseTo() {
    }

    public BaseTo(Integer id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
