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
 * @author Alejo
 */
@Service
public class SetupBook implements Setup {
    
    private static final String QUERY = "INSERT INTO library.book ( title, author, genre, publisher, count)"
            + " VALUES (:title,:author,:genre,:publisher,:count)";

    private static final Faker faker = new Faker();
    public static Integer N = 120;
    
    @Autowired
    private QueryDAO queryDAO;

    @Autowired
    private SystemEntityManager systemEntityManager;

    @Override
    public void setup() {
        try {
            List fields = new ArrayList();
            fields.add(buildField("Title", "String"));
            fields.add(buildField("Author", "String"));
            fields.add(buildField("Genre", "String"));
            fields.add(buildField("Publisher", "String"));
            fields.add(buildField("Count", "Integer"));

            Hash entity = new Hash(
                    "name", "Book", 
                    "icon", "x-fa fa-book", 
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
        
        Integer count = ThreadLocalRandom.current().nextInt(300);
        
        Hash hash = new Hash();
        hash.put("title", faker.book().title());
        hash.put("author", faker.book().author());
        hash.put("genre", faker.book().genre());
        hash.put("publisher", faker.book().publisher());
        hash.put("count", count);
        return hash;
    }
    
}
