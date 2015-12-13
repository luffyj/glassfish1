package com.huotu.demo.glassfish1.service;

import com.huotu.demo.glassfish1.entity1.User;
import com.huotu.demo.glassfish1.entity2.Customer;
import com.huotu.demo.glassfish1.repository.CustomerRepository;
import com.huotu.demo.glassfish1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

/**
 * @author CJ
 */
@Service
public class DemoService {
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @PostConstruct
    @Transactional
    public void init() {
//        User user = new User();
//        userRepository.save(user);
//        Customer customer = new Customer();
//        customerRepository.save(customer);
    }
}
