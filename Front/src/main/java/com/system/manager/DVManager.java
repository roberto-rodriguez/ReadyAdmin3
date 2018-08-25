/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.manager;

import com.system.dao.AbstractBaseDAO;
import com.system.dao.DVDAO;
import com.system.dto.DTO;
import com.system.dto.request.Hash; 
import com.system.model.DVModel;
import com.system.session.Principal;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Roberto
 */
@Service
@Transactional
public class DVManager extends AbstractManager<DVModel, DTO> {

    @Autowired
    private DVDAO dvDao;

    @Autowired
    protected Principal principal;

    @Override
    public AbstractBaseDAO dao() {
        return dvDao;
    }

    public DVModel save(Integer idx, String entityName, Hash data) throws Exception {
        Date creationDate = new Date();

        if (data.getId() == 0) {
            data.put("created_by", principal.getUserId());
            data.put("creation_date", creationDate);
        }

        Integer id = dvDao.saveOrUpdate(idx, entityName, data);
 
        return new DVModel(principal.getName(), creationDate, id);
    }
}
