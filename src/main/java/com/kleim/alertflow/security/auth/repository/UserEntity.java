package com.kleim.alertflow.security.auth.repository;

import com.kleim.alertflow.security.auth.domain.UserDepartment;
import com.kleim.alertflow.security.auth.domain.UserRole;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "hash_password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserDepartment department;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserEntity() {
    }

    public UserEntity(Long id, String login, String password, UserDepartment department, UserRole role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.department = department;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDepartment getDepartment() {
        return department;
    }

    public void setDepartment(UserDepartment department) {
        this.department = department;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
