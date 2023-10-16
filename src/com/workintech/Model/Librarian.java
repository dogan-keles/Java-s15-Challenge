package com.workintech.Model;

import java.util.Objects;
public class Librarian extends Person {
    private String password;
    private boolean isLoggedIn = false;

    public Librarian(String name, int age, int reader_id, String adress, String phoneNumber, String email, String password) {
        super(name, age, reader_id, adress, phoneNumber, email);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), password);
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "password='" + password + '\'' +
                '}';
    }
}
