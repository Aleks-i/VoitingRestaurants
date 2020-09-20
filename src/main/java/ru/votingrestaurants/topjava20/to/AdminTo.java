package ru.votingrestaurants.topjava20.to;

public class AdminTo extends BaseTo {
    private static final long serialVersionUID = 2L;

    public AdminTo() {
    }

    public AdminTo(Integer id, String name, String email, String password) {
        super(id, name, email, password);
    }

    @Override
    public String toString() {
        return "AdminTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
