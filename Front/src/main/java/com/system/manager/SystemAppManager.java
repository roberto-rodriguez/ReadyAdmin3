/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.manager;

import com.dv.dev.SQLInjectionCleanner;
import com.system.dao.AbstractBaseDAO;
import com.system.dao.AppDAO;
import com.system.dao.EntityDAO;
import com.system.dao.QueryDAO;
import com.system.dao.ViewDAO;
import com.system.dto.request.Hash;
import com.system.dto.request.RequestDTO;
import com.system.model.App;
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
public class SystemAppManager extends AbstractManager<App, App> {

    @Autowired
    private Principal principal;

    @Autowired
    private QueryDAO queryDAO;

    @Autowired
    private AppDAO appDAO;

    @Autowired
    private EntityDAO entityDAO;

    @Autowired
    private ViewDAO viewDAO;

    @Autowired
    private SystemUserAppManager userAppManager;

    @Override
    public AbstractBaseDAO dao() {
        return appDAO;
    }

    @Override
    protected App create(Hash data) throws Exception {
        App app = new App();
        app.setCreationDate(new Date());

        String appId = getAppId(data.getString("name"));

        String existSchema = (String) queryDAO.getSession().createSQLQuery("SELECT nspname FROM pg_namespace WHERE nspname = '" + appId + "'").uniqueResult();

        if (existSchema == null) {
            queryDAO.query("CREATE SCHEMA " + appId);
        }

        return app;
    }

    @Override
    protected void update(App app, Hash data) {
        app.setIcon(data.getString("icon"));
        String name = data.getString("name");

        if (app.getName() != name) {
            app.setName(name);
            String idx = getAppId(name);
            app.setIdx(idx);

            if (data.getId() != 0) {
                queryDAO.query("ALTER SCHEMA " + app.getIdx() + " RENAME TO " + idx);
            }
        }
    }

    @Override
    protected void afterSave(App app, Hash data) throws Exception {
        if (data.getId() == 0) {
            data.put("app", app);
            userAppManager.save(data); //data does not have id, so it will create a new UserApp
        }
    }

    private String getAppId(String name) {
        name = name.trim().replaceAll(" ", "");
        String appId = SQLInjectionCleanner.clean(name);

        if (appDAO.exist("idx", appId)) {
            return getAppId(appId + "s");
        }
        return appId;
    }

    public Hash load(RequestDTO request) {
        Integer appId = request.getEntityId();

        Hash app = appDAO.load(appId);

        principal.setAppId(appId);

        String idx = app.getString("idx"); 
        principal.setAppIdx(idx);

        app.put("entities", entityDAO.listByApp(appId));
        app.put("views", viewDAO.listNenuViewsByApp(appId));

        return app;
    }

}
