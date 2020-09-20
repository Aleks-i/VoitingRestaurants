package ru.votingrestaurants.topjava20.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    @Size(min = 5, max = 20)
    @Column(name = "name", nullable = false)
    protected String name;

    @Size(min = 4, max = 20)
    @Column(name = "email", nullable = false)
    protected String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 3, max = 21)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected String password;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    protected AbstractNamedEntity() {
    }

    public AbstractNamedEntity(Integer id, String name, String email, String password, boolean enabled) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return super.toString() + '(' + name + ')';
    }
}