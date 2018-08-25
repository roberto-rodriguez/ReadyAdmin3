/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dao;

import com.system.dto.EntityDTO;
import com.system.dto.RoleEntityDTO;
import com.system.model.RoleEntity;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Alejo
 */
@Repository
public class RoleEntityDAO extends AbstractBaseDAO<RoleEntity, RoleEntityDTO>{
    
    @Override
    public Criteria buildCriteria() {
        return getCriteria()
                .createAlias("role", "role")
                .createAlias("entity", "entity");
    }

    @Override
    public void addOrder(Criteria criteria) {
        criteria.addOrder(Order.asc("entity.title"));
    } 

    @Override
    public void applyListProjection(Criteria criteria) {
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("create").as("create"))
                .add(Projections.property("update").as("update"))
                .add(Projections.property("delete").as("delete"))
                .add(Projections.property("role.id").as("roleId"))
                .add(Projections.property("role.name").as("role"))
                .add(Projections.property("entity.id").as("entityId"))
                .add(Projections.property("entity.idx").as("entityIdx"))
                .add(Projections.property("entity.name").as("entityName"));

        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(RoleEntityDTO.class));
    }
    
    @Transactional
    public List<EntityDTO> getEntityByRole(Integer appRole) { 
        Criteria criteria = buildCriteria();
                criteria.add(Restrictions.eq("role.id", appRole));
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("entity.id").as("id"))
                .add(Projections.property("entity.name").as("name"))
                .add(Projections.property("entity.idx").as("idx"))
                .add(Projections.property("entity.icon").as("icon"));
        
        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(EntityDTO.class));
        return criteria.list();
    }
    
    @Transactional
    public void deleteByEntityIdx(String entityId) { 
        List<RoleEntity> list = getCriteria() 
                .createAlias("entity", "entity")
                .add(Restrictions.like("entity.idx", entityId)) 
                .list();
        
        for (RoleEntity roleEntity : list) {
            delete(roleEntity);
        }
    }
    
    public Boolean exist(Integer idRole, Integer idEntity) {
        return  buildCriteria()
                .add(Restrictions.eq("role.id", idRole))
                .add(Restrictions.eq("entity.id", idEntity))                
                .setMaxResults(1)
                .uniqueResult() != null;       
    }
    
    public Boolean existRole(Integer idRole) {
        return  buildCriteria()
                .add(Restrictions.eq("role.id", idRole))                               
                .setMaxResults(1)
                .uniqueResult() != null;       
    }
    
    public Boolean existEntity(Integer idEntity) {
        return  buildCriteria()
                .add(Restrictions.eq("entity.id", idEntity))                               
                .setMaxResults(1)
                .uniqueResult() != null;       
    }
    
    public Boolean getPermission(Integer idRole, Integer idEntity, String operation){
        Criteria criteria = buildCriteria()
                .add(Restrictions.eq("role.id", idRole))
                .add(Restrictions.eq("entity.id", idEntity));
        
        ProjectionList projectionList = Projections.projectionList();
        
        switch (operation){
            case "delete":
               projectionList.add(Projections.property("delete").as("delete"));
                break;
            case "create":
               projectionList.add(Projections.property("create").as("create"));
                break;
            case "update":
               projectionList.add(Projections.property("update").as("update"));
                break;
        }
        
        return (Boolean) criteria.setProjection(projectionList)
                                    .setMaxResults(1)
                                    .uniqueResult();
        
    }
    
}
