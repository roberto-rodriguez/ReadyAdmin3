/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dao;

import com.system.model.App;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alejo
 */
@Repository
public class AppDAO extends AbstractBaseDAO<App, App>{
    
    public void addOrder(Criteria criteria) {
        criteria.addOrder(Order.asc("name"));
    }  
}
