package com.codegym.model;

import javax.naming.Name;
import javax.persistence.*;

@Entity
@Table(name = "customers")
@NamedQuery(
        name = "findAllCustomer",
        query = "select p from Customer p"
)
@NamedStoredProcedureQuery
        (
                name = "addCustomer",
                procedureName = "addCustomer",
                parameters = {
                        @StoredProcedureParameter(name = "address", mode = ParameterMode.IN, type = String.class),
                        @StoredProcedureParameter(name = "email", mode = ParameterMode.IN, type = String.class),
                        @StoredProcedureParameter(name = "name", mode = ParameterMode.IN, type = String.class),
                        @StoredProcedureParameter(name = "phone", mode = ParameterMode.IN, type = String.class),
                        @StoredProcedureParameter(name = "image", mode = ParameterMode.IN, type = String.class),
                }
        )
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String address;

    private String email;

    private String phone;

    private String image;

    public Customer() {
    }

    public Customer(String name, String address, String email, String phone, String image) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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
}
