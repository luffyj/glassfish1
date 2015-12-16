package com.huotu.demo.glassfish1.controller;

import com.huotu.demo.glassfish1.entity1.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalTime;

/**
 * 只需配置好jta事务管理器,这个类总是可以工作的.
 * @author CJ
 */
//@Controller
public class ContainerManagedController {

    @Autowired
    private EntityManagerFactory nativeEntityManagerFactory;

    @RequestMapping(value = {"/insert3"})
    @ResponseBody
    @Transactional(transactionManager = "jtaTransactionManager")
    public String insert(){
        User user = new User();
        user.setName(LocalTime.now().toString());
        EntityManager manager=nativeEntityManagerFactory.createEntityManager();
        try{
            manager.persist(user);
        }finally {
            manager.close();
        }
//        userRepository.save(user);
        return "inserted "+user.getName();
    }

}
