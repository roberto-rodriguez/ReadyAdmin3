/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dao;

import com.system.dto.FieldDTO;
import com.system.model.Field;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
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
public class FieldDAO extends AbstractBaseDAO<Field, FieldDTO> {

    @Override
    public Criteria buildCriteria() {
        return getCriteria()
                .createAlias("entity", "entity");
    }

    @Override
    public void addOrder(Criteria criteria) {
        criteria.addOrder(Order.asc("name"));
    }

    @Override
    public void applyListProjection(Criteria criteria) {
        ProjectionList projectionList = getEntityFieldsProjection()                
                .add(Projections.property("readonly").as("readonly"))
                .add(Projections.property("grid").as("grid"));

        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(FieldDTO.class));
    }

    public List<FieldDTO> entityFields(Integer entityId) {
        return buildCriteria()
                .add(Restrictions.eq("entity.id", entityId))
                .setProjection(getEntityFieldsProjection())
                .setResultTransformer(Transformers.aliasToBean(FieldDTO.class))
                .list();
    }

    private ProjectionList getEntityFieldsProjection() {
        return Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("ref").as("ref"))
                .add(Projections.property("type").as("type"))
                .add(Projections.property("idx").as("idx"))
                .add(Projections.property("name").as("name"));

    }
    
    
    public List<Field> listByEntity(Integer entityId) {
        return buildCriteria()
                .add(Restrictions.eq("entity.id", entityId)) 
                .list();
    }
   
}
