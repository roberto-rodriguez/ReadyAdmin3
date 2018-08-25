/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.conf.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author rrodriguez 
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({
    "com.system.manager", 
    "com.system.session", 
    "com.system.setup", 
    "com.system.dev", 
    "com.system.util.beans", 
    "com.dv",
    "com.apps.banking" 
})
public class ServiceConfig {  
    
}
