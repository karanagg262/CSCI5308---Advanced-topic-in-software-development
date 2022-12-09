package com.triplify.app.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class UserTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstname;
    private String lastname;
    @NotNull
    @Email(message = "Email is not formatted well here")
    private String emailAddress;
    private boolean isLoggedIn;
    @NotNull
    private String password;

    @Lob
    @Column(name="prof_pic")
    private byte[] profPic;

    public UserTable(){

    }

    public UserTable(Long id, String firstname, String lastname, String emailAddress, String password, boolean isLoggedIn) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
        this.password = password;
        this.isLoggedIn = isLoggedIn;
    }

    public UserTable(@NotBlank String emailAddress, @NotBlank String password){
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public byte[] getProfPicPath() {
        return profPic;
    }

    public void setProfPicPath(byte[] profPic) {
        System.out.println(profPic);
        this.profPic = profPic;
        System.out.println(this.profPic);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserTable)) return false;
        UserTable user = (UserTable) o;
        return Objects.equals(emailAddress, user.emailAddress) &&
                Objects.equals(password, user.password);
    }

    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
    }

    @Override
    public String toString() {
        return "UserTable{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
