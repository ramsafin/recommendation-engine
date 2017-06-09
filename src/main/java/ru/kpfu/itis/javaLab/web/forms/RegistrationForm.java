package ru.kpfu.itis.javaLab.web.forms;

import javax.validation.constraints.Size;

/**
 * Created by Safin Ramil on 07.06.17
 * Safin.Ramil@ordotrans.ru
 */

public class RegistrationForm {

    private String password;

    private String email;

    private String name;

    private String surname;

    public RegistrationForm() {

    }

    @Size(min = 6, max = 127, message = "Email should be within 6 and 127")
    public String getEmail() {
        return email;
    }

    @Size(min = 8, max = 127, message = "Password length should be within 8 and 127")
    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
