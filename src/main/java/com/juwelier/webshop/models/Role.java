package com.juwelier.webshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<Customer> users;

    public Role() {
    }

    public Role(String name) {
        this.roleName = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Customer> getUsers() {
        return users;
    }

    public void setUsers(Set<Customer> users) {
        this.users = users;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
