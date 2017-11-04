package com.praveenpremaratne.securitysample.entities;

    import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String username;
    private String email;
    private String password;


    @Column(insertable = false, updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createdOn;

    public User() {
    }

    public User(String fullName, String username, String email, String password, Date createdOn) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdOn = createdOn;
    }

    public Integer getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
