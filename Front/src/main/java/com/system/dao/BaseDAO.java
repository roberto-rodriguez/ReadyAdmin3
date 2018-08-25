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

import com.system.dto.request.Hash;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rober
 * @param <T>
 * @param <I>
 */
public class BaseDAO<T> {

    protected final Class<T> type;

    @Autowired
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            throw new IllegalStateException("SessionFactory has not been set on DAO before usage");
        }
        return sessionFactory;
    }

    public Session getSession() {
        return getSessionFactory().getCurrentSession();
    }

    protected Class<T> getType() {
        return type;
    }

    public BaseDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Transactional(readOnly = true)
    public T findById(Integer id) {
        return (T) getSession().get(getType(), id);
    }

    @Transactional
    public void delete(T obj) {
        getSession().delete(obj);
    }

    @Transactional
    public void delete(Integer id) {
        getSession().delete(findById(id));
    }

    @Transactional
    public T saveOrUpdate(T obj) {
        getSession().saveOrUpdate(obj);
        return obj;
    }

    @Transactional
    public List<T> list() {
        return getSession().createCriteria(type).list();
    }

    public Criteria getCriteria() {
        return getSession().createCriteria(type);
    }

    public Long total() {
        Long total = (Long) getCriteria().setProjection(Projections.rowCount()).uniqueResult();
        return total == null ? 0 : total;
    }

    public void query(String query, Hash values) {

        for (String k : values.keySet()) {
            query = query.replaceAll("{" + k + "}", values.getString(k));
        }

        query(query);
    }

    public void query(String query) {
        getSession().createSQLQuery(query).executeUpdate();
    }

    public String queryForString(String query) {
        return (String) getSession().createSQLQuery(query).uniqueResult();
    }
}
