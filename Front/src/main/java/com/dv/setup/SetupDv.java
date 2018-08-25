/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dv.setup;

import com.system.dto.request.Hash;
import com.system.enums.ROLE;
import com.system.manager.BaseManager;
import com.system.manager.SystemUserManager;
import com.system.model.User;
import com.system.session.Principal;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rrodriguez
 */
@Service
@Transactional
public class SetupDv extends BaseManager {
    @Autowired
    private Principal principal;
    @Autowired
    private SystemUserManager systemUserManager;
    
    @Autowired
    private SetupAirport setupAirport;

    @Autowired
    private SetupStore setupStore;
    
    @Autowired
    private SetupBank setupBank;
    
    @Autowired
    private SetupLibrary setupLibrary;
    
    @Autowired
    private SetupBeerFactory setupBeerFactory;

    public void setup() {
        User user;
        try {
            user = createUser();        
            principal.setUserId(user.getId());           
            principal.setRoleId(ROLE.ADMIN.getId());
            
            Arrays.stream(new Setup[]{
             setupAirport,
             setupBank,
             setupBeerFactory,
             setupLibrary,
             setupStore
            }).forEach(Setup::setup);
        } catch (Exception ex) {
            Logger.getLogger(SetupDv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private User createUser() throws Exception {
        return systemUserManager.save(new Hash(
                "email", "a",
                "password", "a",
                "firstName", "Roberto",
                "lastName", "Rodriguez"
        ));
    }

}
