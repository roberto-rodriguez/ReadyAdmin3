/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.manager;

import com.system.dao.AbstractBaseDAO;
import com.system.dao.EntityDAO;
import com.system.dao.RoleDAO;
import com.system.dao.RoleEntityDAO;
import com.system.dto.RoleEntityDTO;
import com.system.dto.request.Hash;
import com.system.model.Ent;
import com.system.model.Role;
import com.system.model.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alejo
 */
@Service
@Transactional
public class SystemRoleEntityManager extends AbstractManager<RoleEntity, RoleEntityDTO> {

    @Autowired
    private RoleEntityDAO roleEntityDAO;

    @Autowired
    private RoleDAO roleDAO;
    
    @Autowired
    private EntityDAO entityDAO;

    @Override
    public AbstractBaseDAO dao() {
        return roleEntityDAO;
    }

    @Override
    protected RoleEntity create(Hash data) throws Exception {
        RoleEntity roleEntity = new RoleEntity();
        //TODO 
       Integer roleId = null;
       Ent entity = null;
        if (data.containsKey("entity")) {
            entity = (Ent) data.get("entity");
        } else {
            if (data.containsKey("entityId")) {
                Integer entityId = data.getInt("entityId");
                entity = entityDAO.findById(entityId);
            }
        }
        if (data.containsKey("roleId") && roleId == null) {
            roleId = data.getInt("roleId");
           
        }
        
        Role role = roleDAO.findById(roleId); 

        if (roleEntityDAO.exist(roleId, entity.getId())) {
            throw new RuntimeException("This Role is already associated with this Page.");
        }        
        
        roleEntity.setRole(role); 
        roleEntity.setEntity(entity);
        
        //if (isAdmin != null && isAdmin) {//si no los permisos vienen en data
//            data.put("create", true);
//            data.put("update", true);
//            data.put("delete", true);
            
       // }        

        return roleEntity;
    }

    @Override
    protected void update(RoleEntity entity, Hash data) {
        entity.setCreate(data.getBoolean("create"));
        entity.setUpdate(data.getBoolean("update"));
        entity.setDelete(data.getBoolean("delete"));
    }
}
