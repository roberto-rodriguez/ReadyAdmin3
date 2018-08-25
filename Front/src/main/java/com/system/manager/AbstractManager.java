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
package com.system.manager;

import com.system.dao.AbstractBaseDAO;
import com.system.dto.request.Hash;
import com.system.dto.request.RequestDTO;
import com.system.dto.response.NomenclatorDTO;
import com.system.dto.response.WebResponseData;
import com.system.model.BaseModel;
import com.system.session.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author rrodriguez
 */
public abstract class AbstractManager<T extends BaseModel, D> extends BaseManager {

    @Autowired
    protected Principal principal;

    public Map pageList(RequestDTO request) {
        return dao().pageList(request);
    }

    public Map report(RequestDTO request) {
        return dao().report(request);
    }

    public List<NomenclatorDTO> nomenclatorList(RequestDTO request) {
        return dao().nomenclatorList(request);
    }

    public Hash load(RequestDTO request) {
        Hash hash = dao().load(request);
        completeLoad(hash);
        return hash;
    }

    public Hash load(String app, String entity, Integer id) {
        Hash hash = dao().load(id);
        completeLoad(hash);
        return hash;
    }

    protected void completeLoad(Hash dto) {
        // override this method if need add more things to the DTO
    }

    public abstract AbstractBaseDAO dao();

    protected WebResponseData del(T entity) throws Exception {
        dao().delete(entity);
        return new WebResponseData();
    }

    public WebResponseData delete(Integer idx, String entityName, Hash data) throws Exception {
        T entity;
        Integer id = data.getInt("id");
        entity = (T) dao().findById(id);

        return del(entity);
    }

    public T save(Hash data) throws Exception {
        return save(0, "", data);
    }

    public T save(Integer idDv, String idx, Hash data) throws Exception {
        T entity;
        Boolean creating = data.getId() == 0;

        if (creating) {
            entity = create(idDv, idx, data);
        } else {
            Integer id = data.getId();
            entity = (T) dao().findById(id);
        }

        if (entity != null) {
            update(entity, data);
        }

        entity = (T) dao().saveOrUpdate(entity);

        afterSave(entity, data);

        return entity;
    }

    protected T create(Integer idDv, String idx, Hash data) throws Exception {
        return create(data);
    }

    protected T create(Hash data) throws Exception {
        return null;
    }

    protected void update(T entity, Hash data) throws Exception {
    }

    public T findById(Integer id) {
        return (T) dao().findById(id);
    }

    public T saveOrUpdate(T t) {
        return (T) dao().saveOrUpdate(t);
    }

    public Object getPropertyValueFromEntityId(Integer entityId, String propertyName) {
        return dao().getPropertyValueFromEntityId(entityId, propertyName);
    }

    protected void afterSave(T entity, Hash data) throws Exception {
    } //Redefine if need to do something after save 
}
