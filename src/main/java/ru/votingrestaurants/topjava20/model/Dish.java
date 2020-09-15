package ru.votingrestaurants.topjava20.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "dishes")
public class Dish extends BaseEntity {

    @Size(min = 5, max = 20)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price")
    @NotNull
    private Double price;

    @Column(name = "date")
    @NotNull
    private LocalDate localDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    @JsonIgnore
    private Admin admin;

    public Dish(String name, Double price, LocalDate localDate) {
        this(null, name, price, localDate);
    }

    public Dish(Integer id, String name, Double price, LocalDate localDate) {
        super(id);
        this.name = name;
        this.price = price;
        this.localDate = localDate;
    }

    public Dish() {
    }

    public Dish(Dish dish) {
        this(dish.id, dish.name, dish.price, dish.localDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", localDate=" + localDate +
                ", id=" + id +
                '}';
    }
}
