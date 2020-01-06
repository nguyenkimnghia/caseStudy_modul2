package com.codegym.repository.impl;

import com.codegym.model.Customer;
import com.codegym.repository.CustomerRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CustomerRepositoryImpl implements CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addCustomer(Customer customer) {
        StoredProcedureQuery addCustomer = em.createNamedStoredProcedureQuery("addCustomer");
        addCustomer.setParameter("address", customer.getAddress());
        addCustomer.setParameter("email",customer.getEmail());
        addCustomer.setParameter("name",customer.getName());
        addCustomer.setParameter("phone",customer.getPhone());
        addCustomer.setParameter("image",customer.getImage());
        addCustomer.execute();
    }

    @Override
    public List<Customer> findAll() {
//        StoredProcedureQuery getAllProductsStoredProcedure = em.createNamedStoredProcedureQuery("findAllCustomer");
//        getAllProductsStoredProcedure.execute();
//        List<Customer> customerList = getAllProductsStoredProcedure.getResultList();
//        return customerList;
        List<Customer> customerList = em.createNamedQuery("findAllCustomer").getResultList();
        //TypedQuery<Customer> query = em.createQuery("select p from Customer p", Customer.class);
        //return query.getResultList();
        return customerList;
    }

    @Override
    public Customer login(String name,String phone) {
        List<Customer> customers = em.createNamedQuery("findAllCustomer").getResultList();
        for (int i = 0; i <customers.size() ; i++) {
            if(customers.get(i).getName().equals(name)&&customers.get(i).getPhone().equals(phone)){
                return customers.get(i);
            }
        }
        return null;
    }
}
