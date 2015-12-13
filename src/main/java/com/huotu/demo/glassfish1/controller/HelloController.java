package com.huotu.demo.glassfish1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author CJ
 */
@Controller
public interface HelloController {
    @RequestMapping(value = {"","/"})
    @ResponseBody
//    @Transactional
    String hello();
}
