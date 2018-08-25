/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dao;

import com.system.dto.UserAppDTO;
import com.system.model.UserApp;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alejo
 */
@Repository
public class UserAppDAO extends AbstractBaseDAO<UserApp, UserAppDTO> {
    
    @Override
    public Criteria buildCriteria() {
        return getCriteria()
                .createAlias("user", "user")
                .createAlias("app", "app");
    }
    
    public Integer getIdRole(Integer userId) {
        return (Integer) buildCriteria()
                .add(Restrictions.eq("user.id", userId))                
                .setMaxResults(1)
                .uniqueResult();       
    }
    
    public List<UserAppDTO> getAppsByUser(Integer userId) {
        Criteria criteria = buildCriteria();
                criteria.add(Restrictions.eq("user.id", userId));
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("app.name").as("name"))
                .add(Projections.property("app.id").as("appId"))
                .add(Projections.property("app.idx").as("idx"))
                .add(Projections.property("app.icon").as("icon"))
                .add(Projections.property("role").as("role")) 
                .add(Projections.property("roleId").as("roleId"));
        
        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(UserAppDTO.class));
        return criteria.list();  
    }
    
    
}
