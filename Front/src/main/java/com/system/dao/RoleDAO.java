/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dao;
 
import com.system.dto.RoleDTO;
import com.system.dto.response.NomenclatorDTO;
import com.system.model.Role;
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
public class RoleDAO extends AbstractBaseDAO<Role, RoleDTO>{
    
    @Override
    public Criteria buildCriteria() {
        return getCriteria()
                .createAlias("app", "app");
    }
    
    @Override
    public void addOrder(Criteria criteria) {
        criteria.addOrder(Order.asc("name"));
    } 
     
    //campos a devolver en la consulta
    @Override
    public void applyListProjection(Criteria criteria) {
        criteria.add(Restrictions.eq("app.id", super.principal.getAppId()));
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("name").as("name"))
                .add(Projections.property("isadmin").as("isadmin"))
                .add(Projections.property("description").as("description"))
                .add(Projections.property("app.id").as("appId"))
                .add(Projections.property("app.idx").as("appIdx"));
         
        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(RoleDTO.class));
    }
    
    @Override
    protected void applyNomenclatorProjection(Criteria criteria) {
        //criteria.add(Restrictions.eq("app.id", super.principal.getAppId()));
        criteria.addOrder(Order.asc("name"));
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("name").as("name"))
                .add(Projections.property("description").as("description"));

        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(NomenclatorDTO.class));

    }
    
    @Transactional
    public List<RoleDTO> getRolesByApp(Integer appId) { 
        Criteria criteria = buildCriteria();
                criteria.add(Restrictions.eq("app.id", appId));
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("name").as("name"))
                .add(Projections.property("isadmin").as("isadmin"));
        
        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(RoleDTO.class));
        return criteria.list();
    }
}
