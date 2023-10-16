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

    public boolean authenticate(String enteredName, String enteredPassword) {
        if (this.getName().equals(enteredName) && this.password.equals(enteredPassword)) {
            isLoggedIn = true;
            return true;
        } else {
            isLoggedIn = false;
            return false;
        }
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
