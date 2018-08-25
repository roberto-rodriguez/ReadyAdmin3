/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dao;

import com.system.dto.ViewDTO;
import com.system.model.View;
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
 * @author Rober
 */
@Repository
public class ViewDAO extends AbstractBaseDAO<View, ViewDTO> {

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
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("idx").as("idx"))
                .add(Projections.property("name").as("name"))
                .add(Projections.property("xtype").as("xtype"))
                .add(Projections.property("filters").as("filters"))
                .add(Projections.property("order").as("order"))
                .add(Projections.property("config").as("config"))
                .add(Projections.property("menu").as("menu"))
                .add(Projections.property("entity.idx").as("entity"))
                .add(Projections.property("entity.id").as("idDv"))
                .add(Projections.property("entity.icon").as("iconCls"));

        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(ViewDTO.class));
    }

    public List<ViewDTO> listNenuViewsByApp(Integer appId) {
        Criteria criteria = buildCriteria()
                .createAlias("entity.app", "app")
                .add(Restrictions.eq("menu", true))
                .add(Restrictions.eq("app.id", appId));

        applyListProjection(criteria);

        return criteria.list();
    }

    public List<ViewDTO> listByEntity(Integer entityId) {
        Criteria criteria = buildCriteria().add(Restrictions.eq("entity.id", entityId));

        applyListProjection(criteria);

        return criteria.list();
    }
}
