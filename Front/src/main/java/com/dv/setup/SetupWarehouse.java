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
 * @author Sistemas
 */
@Service
public class SetupWarehouse implements Setup {

    private static final String QUERY = "INSERT INTO store.warehouse ( name, state, city, size)"
            + " VALUES (:name,:state,:city,:size)";

    private static final Faker faker = new Faker();
    public static Integer N = 5;

    @Autowired
    private QueryDAO queryDAO;

    @Autowired
    private SystemEntityManager systemEntityManager;

    @Override
    public void setup() {
        try {
            List warehouseFields = new ArrayList();
            warehouseFields.add(buildField("Name", "String"));
            warehouseFields.add(buildField("State", "String"));
            warehouseFields.add(buildField("City", "String"));
            warehouseFields.add(buildField("Size", "Integer"));

            Hash warehouse = new Hash(
                    "name", "Warehouse",
                    "icon", "x-fa fa-home",
                    "fields", warehouseFields);

            systemEntityManager.save(warehouse);

            for (int i = 0; i < N; i++) {
                queryDAO.query(QUERY, generate());
            }

        } catch (Exception ex) {
            Logger.getLogger(SetupWarehouse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Hash generate() {
        Integer size = ThreadLocalRandom.current().nextInt(200, 2000);
        Hash hash = new Hash();
        hash.put("name", faker.company().name());
        hash.put("state",faker.address().state());
        hash.put("city", faker.address().city());
        hash.put("size", size);
        return hash;
    }
}
