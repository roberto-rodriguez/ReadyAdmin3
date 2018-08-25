/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dao;

import com.system.model.Ent;
import com.system.dto.EntityDTO;
import com.system.dto.response.NomenclatorDTO;
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
 * @author Roberto
 */
@Repository
public class EntityDAO extends AbstractBaseDAO<Ent, EntityDTO> {

    @Override
    public Criteria buildCriteria() {
        return getCriteria().createAlias("app", "app");
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
                .add(Projections.property("icon").as("icon"));

        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(EntityDTO.class));
    }

    public Boolean exist(Integer appId, String idx) {
        return (Long) getCriteria()
                .createAlias("app", "app")
                .add(Restrictions.eq("app.id", appId))
                .add(Restrictions.eq("idx", idx))
                .setProjection(Projections.rowCount())
                .uniqueResult() > 0;
    }

    public Integer getIdByEntityIdx(String idx) {
        return (Integer) getCriteria()
                .add(Restrictions.eq("idx", idx))
                .setProjection(Projections.property("id"))
                .setMaxResults(1)
                .uniqueResult();
    }

    @Transactional
    public void deleteByEntityIdx(String idx) {
        Ent entity = (Ent) getCriteria()
                .add(Restrictions.like("idx", idx))
                .setMaxResults(1)
                .uniqueResult();

        delete(entity);
    }

    public List<EntityDTO> listByApp(Integer appId) {
        Criteria criteria = getCriteria().
                add(Restrictions.eq("app.id", appId));

        applyListProjection(criteria);

        return criteria.list();
    }

}
