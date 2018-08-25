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
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sistemas
 */
@Service
public class SetupProduct implements Setup {

    private static final String QUERY = "INSERT INTO store.product ( name, price, expiration_date, count, on_sale)"
            + " VALUES (:name,:price,:expiration_date,:count,:on_sale)";

    private static final Faker faker = new Faker();
    public static Integer N = 50;
    private static Long NOW = (new Date()).getTime();
    private static final Long EXP = 1878500750419L;

    @Autowired
    private QueryDAO queryDAO;

    @Autowired
    private SystemEntityManager systemEntityManager;

    @Override
    public void setup() {
        try {
            List fields = new ArrayList();
            fields.add(buildField("Name", "String"));
            fields.add(buildField("Price", "Double"));
            fields.add(buildField("Expiration Date", "Date"));
            fields.add(buildField("Count", "Integer"));
            fields.add(buildField("On Sale", "Boolean"));

            Hash entity = new Hash(
                    "name", "Product", 
                    "icon", "x-fa fa-gift", 
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
        Double price = faker.number().randomDouble(2, 0, 200);
        Integer count = ThreadLocalRandom.current().nextInt(30);
        Boolean onSale = ThreadLocalRandom.current().nextInt(0, 2) != 0;
        Hash hash = new Hash();
        hash.put("name", faker.book().title());
        hash.put("price", price);
        hash.put("expiration_date", new Date(ThreadLocalRandom.current().nextLong(NOW, EXP)));
        hash.put("count", count);
        hash.put("on_sale", onSale);
        return hash;
    }

}
