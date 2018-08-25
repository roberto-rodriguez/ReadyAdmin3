/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dao;

import com.dv.criteria.DVCriteria; 
import com.system.dto.FieldDTO;
import com.system.dto.UserDTO;
import com.system.dto.request.Hash;
import com.system.dto.request.RequestDTO;
import com.system.model.User;
import com.system.session.Principal;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Roberto
 */
@Repository
public class DVDAO extends AbstractBaseDAO<User, UserDTO> {

    @Autowired
    private Principal principal;

    @Autowired
    private FieldDAO fieldDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public DVCriteria buildCriteria(RequestDTO request) {
        //TODO, take/put fields cached from the Session
        List<FieldDTO> fields = fieldDAO.entityFields(request.getEntityId());
        String order = queryForString("SELECT _order FROM entity WHERE id = " + request.getEntityId());
        return new DVCriteria(jdbcTemplate, request, fields, order);
    }
    
    @Override
    public Map report(RequestDTO request) {           
       
        Criteria criteria = buildCriteria(request);

        applyListProjection(criteria);

        Map result = new HashMap();

        result.put("data", criteria.list());
        if (request.getEntityId() != 0) {//if entity is DV
            List<FieldDTO> fields = fieldDAO.entityFields(request.getEntityId());
           
            result.put("fields", fields);
        }       

        return result;
    }

    @Override
    protected Long count(Criteria criteria) {
        return ((DVCriteria) criteria).count();
    }

    public Hash load(RequestDTO request) {
        return (Hash) buildCriteria(request).uniqueResult();
    }

    public Integer saveOrUpdate(Integer idx, String entityName, Hash hash) {
        System.out.println("DVDAO -> insert" + hash);
        String app = principal.getAppIdx();

        StringBuilder sb = new StringBuilder();

        //TODO, get fields cached from the Session
        List<FieldDTO> fields = fieldDAO.entityFields(idx);

        fields = fields.stream()
                .filter(field -> {
                    if (field.getIdx().equalsIgnoreCase("id")) {
                        return false;//Never save or update id
                    }

                    if (hash.getId() != 0) {
                        return !field.getReadonly();//Never update read only fields
                    }
                    return true;
                }).collect(Collectors.toList());

        
        if (hash.getId() == 0) {
            sb.append("INSERT INTO " + app + "." + entityName);
            sb.append(fieldsToString(fields, field -> field.getIdx()));
            sb.append(" VALUES ");
            sb.append(fieldsToString(fields, field -> "?"));
        } else { 
            sb.append("UPDATE " + app + "." + entityName + " SET ");
            sb.append(fields.stream().map(f -> f.getIdx() + " = ? ").collect(Collectors.joining(", ")));
            sb.append(" WHERE id = " + hash.getId());
        }

        Object[] values = fields.stream().map(field -> {
            String name = field.getIdx();

            switch (field.getType()) {
                case "Boolean":
                    return hash.getBoolean(name);
                case "Date":
                    return hash.getDate(name);
                case "Double":
                    return hash.getDouble(name);
                case "Integer":
                case "User":
                    return hash.getInteger(name);
                default:
                    return hash.get(name);
            }
        }).toArray();

        int[] types = fields.stream().mapToInt(field -> {
            switch (field.getType()) {
                case "Boolean":
                    return Types.BOOLEAN;
                case "Date":
                    return Types.TIMESTAMP;
                case "Double":
                    return Types.DOUBLE;
                case "Integer":
                case "User":
                    return Types.INTEGER;
                default:
                    return Types.VARCHAR;
            }
        }).toArray();

        Integer result = jdbcTemplate.update(sb.toString(), values, types);

        if (hash.getId() == 0) {
            return result;  //If is saving, result is the new id
        } else {
            return hash.getId();//If is updating, result is the number of rows affected, so just return the initial id
        }
    }

    private String fieldsToString(List<FieldDTO> fields, Function<FieldDTO, String> function) {
        return " (" + fields.stream().map(function).collect(Collectors.joining(", ")) + ") ";
    }
}
