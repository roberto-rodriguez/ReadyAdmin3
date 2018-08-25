/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.manager;

import com.system.model.Ent;
import com.system.model.Field;
import com.system.manager.*;
import com.system.dao.AbstractBaseDAO;
import com.system.dao.FieldDAO;
import com.system.dto.request.Hash;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Roberto
 */
@Service
@Transactional
public class SystemFieldManager extends AbstractManager<Field, Field> {

    @Autowired
    private FieldDAO fieldDAO; 

    @Override
    public AbstractBaseDAO dao() {
        return fieldDAO;
    }

    @Override
    protected Field create(Hash data) throws Exception {
        Field field = new Field();

        Ent entity = (Ent) data.get("entity");

        field.setEntity(entity);
        
        //TODO por que no lo agarra directo del hash
        field.setIdx(data.getString("idx"));
        field.setName(data.getString("name"));
        field.setType(data.getString("type"));
        field.setReadonly(data.getBoolean("readonly", Boolean.FALSE));
        return field;
    }

    @Override
    protected void update(Field field, Hash data) {
        boolean creating = data.getBoolean("creating", Boolean.FALSE);
        boolean updating = data.getBoolean("updating", Boolean.FALSE); 

//        if (creating || updating) {
            String name= data.getString("name");
            String idx = data.getString("idx");

            field.setName(name);
            field.setIdx(idx);
            field.setGrid(data.getBoolean("grid", Boolean.FALSE)); 
            field.setType(data.getString("type"));
            field.setRef(data.getString("ref"));
//        }
    }

    public List<Field> listByEntity(Integer entityId) {
        return fieldDAO.listByEntity(entityId);
    }
}
