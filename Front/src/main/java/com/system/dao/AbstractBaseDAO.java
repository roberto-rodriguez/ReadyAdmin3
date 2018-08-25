/*
 ** File: WebResponse.java
 **
 ** Date Created: December 2016
 **
 ** Copyright @ 2016-2018 Roberto Rodriguez.
 ** Email: robertoSoftwareEngineer@gmail.com
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Roberto Rodriguez.
 **
 */
package com.system.dao;

import com.system.dto.FieldDTO;
import com.system.dto.request.Hash;
import com.system.dto.request.RequestDTO;
import com.system.dto.response.NomenclatorDTO;
import com.system.model.BaseModel;
import com.system.session.Principal;
import com.system.util.ObjectHasher;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author rrodriguez
 */
public class AbstractBaseDAO<T extends BaseModel, D> extends BaseDAO<T> {
    
    
    @Autowired
    protected Principal principal;
    
            
    public Map pageList(RequestDTO request) {           
       
        Criteria criteria = buildCriteria(request)
                .setFirstResult(request.getStart());

        if (request.getLimit() != 0) {
            criteria.setMaxResults(request.getLimit());
        }

        applyListProjection(criteria);

        addOrder(criteria);

        Map result = new HashMap();

        result.put("List", criteria.list());

        if (request.getLimit() != 0) {
            result.put("page", request.getPage());
            Long total = count(buildCriteria(request));
            result.put("TotalCount", total);
        }
        return result;
    }
    
    public Map report(RequestDTO request) {           
       
        Criteria criteria = buildCriteria(request);

        applyListProjection(criteria);

        Map result = new HashMap();

        result.put("data", criteria.list());      

        return result;
    }

    public Criteria buildCriteria() {
        return getCriteria();
    }

    protected Criteria buildCriteria(RequestDTO request) {
        return buildCriteriaWithParams(buildCriteria(), request.getExpressions());
    }

    protected Criteria buildCriteriaWithParams(Criteria criteria, List<Criterion> expressions) {
        System.out.println("AbstractBaseDAO:: buildCriteriaWithParams");
        for (Criterion expression : expressions) {
            System.out.println("" + expression.toString());
            criteria.add(expression);
        }

        return criteria;
    }

    protected Long count(Criteria criteria) {
        System.out.println("AbstractBaseDAO -> count");
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    public List<NomenclatorDTO> nomenclatorList(RequestDTO request) {
        Criteria criteria = buildCriteria(request);

        applyNomenclatorProjection(criteria);

        return criteria.list();
    }

    public Hash load(RequestDTO request) {
        Criteria critera = buildCriteria(request);

        applyLoadProjection(critera);

        D dto = (D) critera.uniqueResult();

        return ObjectHasher.toHash(dto);
    }

    public Hash load(Integer id) {
        Criteria criteria = buildCriteria().add(Restrictions.eq("id", id));

        applyLoadProjection(criteria);

        D dto = (D) criteria.uniqueResult();

        return ObjectHasher.toHash(dto);
    }

    protected void applyLoadProjection(Criteria criteria) {
        applyListProjection(criteria); // override this method if need different projection
    }

    protected void applyListProjection(Criteria criteria) {
    } // override this method if need different projection

    protected void applyReportProjection(Criteria criteria, String reportType) {
    } // override this method if need different projection

    public void addOrder(Criteria criteria) {
        //Override this if need to add Order to the list
    }

    public NomenclatorDTO getNomenclatorById(Integer id) {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.eq("id", id));
        applyNomenclatorProjection(criteria);
        return (NomenclatorDTO) criteria.uniqueResult();
    }

    protected void applyNomenclatorProjection(Criteria criteria) {
        criteria.addOrder(Order.asc("name"));
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("name").as("name"));

        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(NomenclatorDTO.class));

    }

    public Object getPropertyValueFromEntityId(Integer entityId, String propertyName) {
        return getCriteria().add(Restrictions.eq("id", entityId))
                .setProjection(Projections.property(propertyName))
                .setMaxResults(1)
                .uniqueResult();
    }

    public boolean exist(String property, Object value) {
        return (Long) getCriteria()
                .add(Restrictions.eq(property, value))
                .setProjection(Projections.rowCount())
                .uniqueResult() > 0;
    }
}
