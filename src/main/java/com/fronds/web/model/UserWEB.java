package com.fronds.web.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Qbek on 2016-12-11.
 */
public class UserWEB {
    private long id;
    @NotNull
    @Size(min=1, max=20, message="{firstName.size}")
    private String firstName;
    @NotNull
    @Size(min=1, max=25, message="{lastName.size}")
    private String lastName;
    @NotNull
    @Size(min=5, max=20, message="{login.size}")
    private String login;
    @NotNull
    @Size(min=5, max=25, message="{password.size}")
    private String password;
    private String profilePicture;
    private long registrationDate;

    public UserWEB() {
    }

    public UserWEB(long id, String login, String password, String firstName, String lastName, String profilePicture, long registrationDate) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.registrationDate = registrationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public long getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(long registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserWEB userWEB = (UserWEB) o;

        if (id != userWEB.id) return false;
        if (registrationDate != userWEB.registrationDate) return false;
        if (login != null ? !login.equals(userWEB.login) : userWEB.login != null) return false;
        if (password != null ? !password.equals(userWEB.password) : userWEB.password != null) return false;
        if (firstName != null ? !firstName.equals(userWEB.firstName) : userWEB.firstName != null) return false;
        if (lastName != null ? !lastName.equals(userWEB.lastName) : userWEB.lastName != null) return false;
        return profilePicture != null ? profilePicture.equals(userWEB.profilePicture) : userWEB.profilePicture == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (profilePicture != null ? profilePicture.hashCode() : 0);
        result = 31 * result + (int) (registrationDate ^ (registrationDate >>> 32));
        return result;
    }
}
