package com.codegym.repository;

import com.codegym.model.Customer;

import java.util.List;


public interface GeneralRepository<E> {


    void addCustomer(Customer customer);
    List<E> findAll();
    E login(String name, String phone);
}
