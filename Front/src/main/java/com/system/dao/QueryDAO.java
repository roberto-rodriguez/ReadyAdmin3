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
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Rober
 * @param <T>
 * @param <I>
 */
@Repository
public class QueryDAO {

    @Autowired
    private SessionFactory sessionFactory;

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

    public void query(String query) {
        getSession().createSQLQuery(query).executeUpdate();
    }

    public void query(String queryStr, Hash values) {
        SQLQuery query = getSession().createSQLQuery(queryStr);

        for (String key : values.keySet()) {
            query.setParameter(key, values.get(key));
        }

        query.executeUpdate();
    }

    public String queryForString(String query) {
        return (String) getSession().createSQLQuery(query).uniqueResult();
    }
}
