package ru.votingrestaurants.topjava20.to;

import java.io.Serializable;

public class UserTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    public UserTo() {
    }

    public UserTo(Integer id, String name, String email, String password) {
        super(id, name, email, password);
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
