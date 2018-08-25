/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dv.setup;

import static com.dv.setup.SetupProduct.N;
import static com.dv.setup.SetupStore.buildField;
import com.github.javafaker.Faker;
import com.system.dao.QueryDAO;
import com.system.dto.request.Hash;
import com.system.manager.SystemEntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alejo
 */
@Service
public class SetupAirPlane implements Setup {
    
    private static final String QUERY = "INSERT INTO airport.airplane ( name, weight, capacity, size)"
            + " VALUES (:name,:weight,:capacity,:size)";

    private static final Faker faker = new Faker();
    public static Integer N = 5;    

    @Autowired
    private QueryDAO queryDAO;

    @Autowired
    private SystemEntityManager systemEntityManager;
    
    @Override
    public void setup() {
        try {
            List fields = new ArrayList();
            fields.add(buildField("Name", "String"));
            fields.add(buildField("Weight", "Double"));
            fields.add(buildField("Capacity", "Integer"));
            fields.add(buildField("Size", "Double"));
            

            Hash entity = new Hash(
                    "name", "Airplane", 
                    "icon", "x-fa fa-plane", 
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
        Double weight = faker.number().randomDouble(2, 2, 7);
        Integer capacity = ThreadLocalRandom.current().nextInt(100, 300);
        Double size = faker.number().randomDouble(2, 50, 100);
        Hash hash = new Hash();
        hash.put("name", "Airplane "+faker.color().name());
        hash.put("weight", weight);
        hash.put("capacity",capacity);
        hash.put("size", size);
        return hash;
    }
    
}
