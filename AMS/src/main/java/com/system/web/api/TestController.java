/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.web.api;
 

import javax.mail.MessagingException;
import javax.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Roberto Rodriguez
 */
@RestController
@RequestMapping(value = "/api")
@Path("/api")
public class TestController {
    
//    @Autowired
//    protected SecurityService securityService;
//
//    @RequestMapping(value = "/login",method = RequestMethod.GET)
//    public @ResponseBody Principal login() throws MessagingException{
//        System.out.println("login...");
// 
//        return securityService.login("a", "b", APP.AMS);
//    }
   
 
}
