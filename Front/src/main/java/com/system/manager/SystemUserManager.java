/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.manager;

import com.system.dao.AbstractBaseDAO;
import com.system.dao.UserDAO;
import com.system.dto.UserDTO;
import com.system.dto.request.Hash;
import com.system.dto.response.WebResponseData;
import com.system.model.User;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alejo
 */
@Service
@Transactional
public class SystemUserManager extends AbstractManager<User, UserDTO> {
    
    @Autowired
    private UserDAO userDAO;
    
    @Autowired
    SystemUserAppManager systemUserAppManager;
    
    @Override
    public AbstractBaseDAO dao() {
        return userDAO;
    }
    
    @Override
    protected User create(Hash data) throws Exception {
        if (userDAO.exist("email", data.getString("email"))) {
            throw new RuntimeException("There is already an user with that identifier, enter another");
        }
        
        User user = new User();
        user.setActive(true);
        user.setCreationDate(new Date());
        user.setLastLogin(new Date()); 
        return user;
    }
    
    @Override
    protected void update(User entity, Hash data) {
        entity.setFirstName(data.getString("firstName"));
        entity.setLastName(data.getString("lastName"));
        entity.setEmail(data.getString("email"));
        entity.setPassword(data.getString("password"));        
    }
    
    @Override
    public WebResponseData del(User entity) throws Exception {
        
        if (!entity.getActive()) {
            dao().delete(entity);
            return new WebResponseData();
        }
        return new WebResponseData(500, "User can not be deleted while is active.");
    }    
}
