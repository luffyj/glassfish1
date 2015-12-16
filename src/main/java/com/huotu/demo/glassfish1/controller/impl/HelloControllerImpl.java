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
import java.time.LocalTime;

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
        log.info("user access /");

        Customer customer = new Customer();
        customerRepository.save(customer);

//        User user = new User();
//        userRepository.save(user);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<ul>");
        userRepository.findAll().forEach(user -> {
            stringBuilder.append("<li>")
            .append(user.getName())
            .append("</li>");
        });
        stringBuilder.append("</ul>");

        return stringBuilder.toString();
    }

    @Override
    @RequestMapping(value = {"/insert"})
    @ResponseBody
    @Transactional
    public String insert(){
        while (userRepository.count()<2){
            User user = new User();
            user.setName(LocalTime.now().toString());
            userRepository.save(user);
        }
        // 取出2个 分别修改为当前时间
        userRepository.findAll().forEach(
                user -> {
                    user.setName(LocalTime.now().toString());
                }
        );

//        com.atomikos.icatch.standalone.UserTransactionServiceImp userTransactionServiceImp;

        return "inserted ";
    }
}
