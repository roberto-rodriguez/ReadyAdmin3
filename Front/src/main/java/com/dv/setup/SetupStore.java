/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dv.setup;

import com.system.dto.request.Hash;
import com.system.manager.SystemAppManager;
import com.system.model.App;
import com.system.session.Principal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Roberto
 */
@Service
public class SetupStore implements Setup {

    @Autowired
    private Principal principal;

    @Autowired
    private SystemAppManager systemAppManager;
    
    @Autowired
    private SetupProduct setupProduct;
    @Autowired
    private SetupWarehouse setupWarehouse;


    @Override
    public void setup() {
        try {            
            App app = createApp();
            principal.setAppId(app.getId());
            principal.setAppIdx(app.getIdx()); 
            
            Arrays.stream(new Setup[]{
             setupProduct,
             setupWarehouse
            }).forEach(Setup::setup);
        } catch (Exception ex) {
            Logger.getLogger(SetupStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private App createApp() throws Exception {
        return systemAppManager.save(new Hash(
                "name", "Store",
                "icon", "x-fa fa-shopping-cart"
        ));
    }

    public static LinkedHashMap buildField(String name, String type) {
        LinkedHashMap field = new LinkedHashMap();
        field.put("name", name);
        field.put("type", type);
        return field;
    }

}