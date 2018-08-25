/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.manager;

import com.dv.dev.SQLInjectionCleanner;
import com.system.model.Ent;
import com.system.dao.AbstractBaseDAO;
import com.system.dao.EntityDAO;
import com.system.dao.ViewDAO;
import com.system.dto.ViewDTO;
import com.system.dto.request.Hash;
import com.system.model.View;
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
public class SystemViewManager extends AbstractManager<View, ViewDTO> {

    @Autowired
    private ViewDAO viewDAO;

    @Autowired
    private EntityDAO entityDAO;

    @Override
    public AbstractBaseDAO dao() {
        return viewDAO;
    }

    @Override
    protected View create(Hash data) throws Exception {
        View view = new View();

        Integer idDv = data.getInt("idDv");
        Ent entity = (Ent) entityDAO.findById(idDv);

        view.setEntity(entity);

        view.setCreationDate(new Date());
        view.setXtype(data.getString("xtype"));
        return view;
    }

    @Override
    protected void update(View view, Hash data) {
        String name = data.getString("name");
        String idx = SQLInjectionCleanner.clean(name);

        view.setName(name);
        view.setIdx(idx);
        view.setFilters(data.getString("filters"));
        view.setOrder(data.getString("order"));
        view.setConfig(data.getString("config"));
        view.setMenu(data.getBoolean("menu"));
    }
 
}
