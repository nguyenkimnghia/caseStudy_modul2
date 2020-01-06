package com.codegym.service;

import java.util.List;

public interface GeneralService <E>{

    void AddCustomer(E e);
    List<E> findAll();
    E login(String name, String phone);
}
