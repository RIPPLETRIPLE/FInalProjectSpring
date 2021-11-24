package com.example.finalprojectspring.model.entity;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private long id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status;

    private User(String login, String password, Role role, UserStatus status) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public User() {

    }

    public static User createUser(String login, String password, Role role, UserStatus status) {
        return new User(login, password, role, status);
    }

    public static User createUser(long id, String login, String password, Role role, UserStatus status) {
        User user = new User(login, password, role, status);
        user.setId(id);
        return user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            return login.equals(((User) obj).login) && password.equals(((User) obj).password);
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }



    public enum Role {
        Admin, User, Guest
    }
    public enum UserStatus {
        Blocked, Unblocked;
    }
}
