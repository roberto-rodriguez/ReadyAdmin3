/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dv.setup;

import static com.dv.setup.SetupStore.buildField;
import com.github.javafaker.Faker;
import com.system.dao.QueryDAO;
import com.system.dto.request.Hash;
import com.system.manager.SystemEntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alejo
 */
@Service
public class SetupOffice implements Setup{
    
    private static final String QUERY = "INSERT INTO bank.office ( name, city, state, zip_code)"
            + " VALUES (:name,:city,:state,:zip_code)";

    private static final Faker faker = new Faker();
    public static Integer N = 10;    

    @Autowired
    private QueryDAO queryDAO;

    @Autowired
    private SystemEntityManager systemEntityManager;
    
    @Override
    public void setup() {
        try {
            List fields = new ArrayList();
            fields.add(buildField("Name", "String"));
            fields.add(buildField("City", "String"));
            fields.add(buildField("State", "String"));
            fields.add(buildField("Zip code", "String"));
            

            Hash entity = new Hash(
                    "name", "Office", 
                    "icon", "x-fa fa-building", 
                    "fields", fields);

            systemEntityManager.save(entity);
             
            for (int i = 0; i < N; i++) {
                queryDAO.query(QUERY, generate());
            }
        } catch (Exception ex) {
            Logger.getLogger(SetupProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Hash generate() {
        
        Hash hash = new Hash();
        hash.put("name", faker.company().name());
        hash.put("city", faker.address().city());
        hash.put("state",faker.address().state());
        hash.put("zip_code", faker.address().zipCode());
        return hash;
    }
    
}
