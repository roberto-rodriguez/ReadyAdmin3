/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.manager;

import com.system.dao.AbstractBaseDAO; 
import com.system.dao.UserAppDAO;
import com.system.dao.UserDAO;
import com.system.dto.UserAppDTO;
import com.system.dto.request.Hash;
import com.system.enums.ROLE;
import com.system.model.App;
import com.system.model.User;
import com.system.model.UserApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alejo
 */
@Service
@Transactional
public class SystemUserAppManager extends AbstractManager<UserApp, UserAppDTO> {

    @Autowired
    private UserAppDAO userAppDAO;

    @Autowired
    private UserDAO userDAO; 

    @Override
    public AbstractBaseDAO dao() {
        return userAppDAO;
    }

    @Override
    protected UserApp create(Hash data) throws Exception {
        App app = (App) data.get("app"); 
        User user = userDAO.findById(principal.getUserId());

        return new UserApp(user, app);
    }

    @Override
    protected void update(UserApp entity, Hash data) {
        Integer roleId = data.getInt("roleId");

        if (roleId == null) {
            roleId = ROLE.ADMIN.getId();
        }

        entity.setRoleId(roleId);
    }

}
