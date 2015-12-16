package com.huotu.demo.glassfish1.controller;

import com.huotu.demo.glassfish1.entity1.User;
import com.huotu.demo.glassfish1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalTime;

/**
 * @author CJ
 */
@Controller
//@EnableConfigurationProperties
public class AnotherController {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = {"/insert2"})
    @ResponseBody
    @Transactional(transactionManager = "transactionManager")
    public String insert(){
        User user = new User();
        user.setName(LocalTime.now().toString());
        userRepository.saveAndFlush(user);
        return "inserted "+user.getName();
    }
}
