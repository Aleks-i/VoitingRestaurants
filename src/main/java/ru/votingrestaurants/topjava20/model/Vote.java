package ru.votingrestaurants.topjava20.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "votes")
public class Vote extends BaseEntity {

    @Column(name = "date")
    private LocalDate localDate;

    @Column(name = "time")
    private LocalTime localTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @Column(name = "user_id")
    private int user_id;

    public Vote(Integer id, LocalDate localDate, LocalTime localTime, int user_id) {
        super(id);
        this.localDate = localDate;
        this.localTime = localTime;
        this.user_id = user_id;
    }

    public Vote() {
    }

    public Vote(Vote vote) {
        this(vote.id, vote.localDate, vote.localTime, vote.user_id);
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "localDate=" + localDate +
                ", localTime=" + localTime +
                ", user_id=" + user_id +
                ", id=" + id +
                '}';
    }
}
