/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dv.dev;

import com.system.dao.QueryDAO;
import com.system.dto.request.Hash;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import static com.dv.dev.SQLInjectionCleanner.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author roberto.rodriguez
 */
@Component
@Transactional
public class DBGenerator {

    @Autowired
    private QueryDAO queryDAO;

    public void createSquema(String app) {
        queryDAO.query("create schema " + clean(app));
    }

    public void createTable(String squema, String entity, List<Hash> fields) {

        StringBuilder sb = new StringBuilder("CREATE TABLE " + squema + "." + entity + " (id serial NOT NULL ");
        StringBuilder fks = new StringBuilder();

        for (Hash field : fields) {
            String name = field.getString("idx");

            if (name == null || name.equalsIgnoreCase("id")) {
                continue; //Id was already included
            }

            System.out.println("Adding field: " + name);

            sb.append(", " + name + " ");

            switch (field.getString("type")) {
                case "Boolean":
                    sb.append("boolean");
                    break;
                case "Date":
                    sb.append("timestamp without time zone");
                    break;
                case "Integer":
                case "Entity":
                case "User":
                    sb.append("integer");
                    break;
                case "Double":
                    sb.append("double precision");
                    break;
                default:
                    sb.append("character varying(255)");
                    break;
//                case "Entity":
//                case "User":
//                    sb.append("serial NOT NULL");
//                    String fieldName = field.getString("idx");
//                    String reference = field.getString("ref");
//                    fks.append(", CONSTRAINT " + squema + "_" + entity + "_" + fieldName + "_fk ");
//                    fks.append("FOREIGN KEY (" + fieldName + ") ");
//                    
//                    String referencedTable = reference.split("\\.")[0];
//                    
//                    fks.append("REFERENCES " + referencedTable);
//                    fks.append(" (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION");
//                    break;
            }
        }

        sb.append(" , CONSTRAINT " + squema + "_" + entity + "_pk PRIMARY KEY (id) ");
        sb.append(fks.toString());
        sb.append(" )");

        queryDAO.query(sb.toString());
    }

}
