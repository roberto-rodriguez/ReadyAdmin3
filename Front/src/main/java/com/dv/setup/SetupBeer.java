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
public class SetupBeer implements Setup{
    
    private static final String QUERY = "INSERT INTO beerfactory.beer ( name, malt, style, cost, price)"
            + " VALUES (:name,:malt,:style,:cost,:price)";

    private static final Faker faker = new Faker();
    public static Integer N = 50;
    
    @Autowired
    private QueryDAO queryDAO;

    @Autowired
    private SystemEntityManager systemEntityManager;

    @Override
    public void setup() {
        try {
            List fields = new ArrayList();
            fields.add(buildField("Name", "String"));
            fields.add(buildField("Malt", "String"));
            fields.add(buildField("Style", "String"));
            fields.add(buildField("Cost", "Double"));
            fields.add(buildField("Price", "Double"));

            Hash entity = new Hash(
                    "name", "Beer", 
                    "icon", "x-fa fa-beer", 
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
      Double cost = faker.number().randomDouble(2, 1, 11);
      Double price = faker.number().randomDouble(2, 15, 50);
        
        Hash hash = new Hash();
        hash.put("name", faker.beer().name());
        hash.put("malt", faker.beer().malt());
        hash.put("style", faker.beer().style());
        hash.put("cost", cost);
        hash.put("price", price);
        return hash;
    }
    
}
