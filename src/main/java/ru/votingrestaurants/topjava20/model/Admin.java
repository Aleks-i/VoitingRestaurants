package ru.votingrestaurants.topjava20.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "admins")
public class Admin extends BaseEntity {

    @Size(min = 5, max = 20)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(min = 4, max = 20)
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 20)
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "admin")
    @JsonIgnore
    private List<Dish> lunchMenu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "admin")
    @JsonIgnore
    private List<Vote> voteList;

    public Admin(String name, String email, String password) {
        this(null, name, email, password);
    }

    public Admin(Integer id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Admin() {
    }

    public Admin(Admin admin) {
        this(admin.id, admin.name, admin.email, admin.password);
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

    public List<Dish> getLunchMenu() {
        return lunchMenu;
    }

    public void setLunchMenu(List<Dish> lunchMenu) {
        this.lunchMenu = lunchMenu;
    }

    public List<Vote> getVoteList() {
        return voteList;
    }

    public void setVoteList(List<Vote> voteList) {
        this.voteList = voteList;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
