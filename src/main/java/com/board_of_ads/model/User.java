package com.board_of_ads.model;

public class User {
    private Long id;
    private String email;
    private String password;
    private String firsName;
    private String lastName;
    private int phone;
    private int dataRegistration;
    private boolean enable;

    public User() {
    }

    public User(Long id, String email, String password, String firsName, String lastName, int phone, int dataRegistration, boolean enable) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firsName = firsName;
        this.lastName = lastName;
        this.phone = phone;
        this.dataRegistration = dataRegistration;
        this.enable = enable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getDataRegistration() {
        return dataRegistration;
    }

    public void setDataRegistration(int dataRegistration) {
        this.dataRegistration = dataRegistration;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
