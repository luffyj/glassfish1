package com.huotu.demo.glassfish1.controller.impl;

import com.huotu.demo.glassfish1.controller.HelloController;
import com.huotu.demo.glassfish1.entity1.User;
import com.huotu.demo.glassfish1.entity2.Customer;
import com.huotu.demo.glassfish1.repository.CustomerRepository;
import com.huotu.demo.glassfish1.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.RollbackException;

/**
 * @author CJ
 */
@SuppressWarnings("ALL")
@Controller
public class HelloControllerImpl implements HelloController {

    private static final Log log = LogFactory.getLog(HelloControllerImpl.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @RequestMapping(value = {"", "/"})
    @ResponseBody
    @Transactional
    public String hello() {
//        com.sun.enterprise.
//        JavaEETransaction javaEETransaction;
        RollbackException rollbackException;
        log.info("user access /");
        User user = new User();
        userRepository.save(user);
        Customer customer = new Customer();
        customerRepository.save(customer);
        return "hello";
    }
}
