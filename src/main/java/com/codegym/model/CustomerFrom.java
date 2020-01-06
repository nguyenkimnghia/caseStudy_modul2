package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class CustomerFrom {
    private int id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private MultipartFile image;

    public CustomerFrom() {
    }

    public CustomerFrom(String name, String address, String email, String phone, MultipartFile image) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
