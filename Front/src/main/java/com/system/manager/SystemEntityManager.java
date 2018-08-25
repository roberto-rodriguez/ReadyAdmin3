/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.manager;

import com.dv.dev.DBGenerator;
import com.dv.dev.DvPdfGenerator;
import com.system.dao.EntityDAO;
import com.dv.dev.SQLInjectionCleanner;
import com.system.model.Ent;
import com.system.dao.AbstractBaseDAO;
import com.system.dao.AppDAO;
import com.system.dao.FieldDAO;
import com.system.dao.RoleEntityDAO;
import com.system.dao.ViewDAO;
import com.system.dto.request.Hash;
import com.system.dto.request.RequestDTO;
import com.system.model.App;
import com.system.model.Field;
import com.system.session.Principal;
import com.system.util.IOUtil;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Roberto
 */
@Service
@Transactional
public class SystemEntityManager extends AbstractManager<Ent, Ent> {

    @Autowired
    private Principal principal;

    @Autowired
    private EntityDAO entityDAO;

    @Autowired
    private ViewDAO viewDAO;

    @Autowired
    private FieldDAO fieldDAO;

    @Autowired
    private AppDAO appDAO;

    @Autowired
    private RoleEntityDAO roleEntityDAO;

    @Autowired
    private SystemFieldManager systemFieldManager;

    @Autowired
    private DBGenerator dBGenerator;

    @Autowired
    @Qualifier("dvPdfGenerator")
    private DvPdfGenerator dvPdfGenerator;

    @Override
    public AbstractBaseDAO dao() {
        return entityDAO;
    }

    @Override
    protected Ent create(Hash data) throws Exception {
        Ent entity = new Ent();
        entity.setCreationDate(new Date());

        App app = appDAO.findById(principal.getAppId());

        entity.setApp(app);

        String idx = updateName(entity, data);
        data.put("idx", idx); //Entity idx, calculated from the name

        List<Hash> fields = data.getHashList("fields");

        for (Hash field : fields) {
            field.put("idx", SQLInjectionCleanner.clean(field.getString("name")));
        }

        data.replace("fields", fields);

        dBGenerator.createTable(app.getIdx(), idx, fields);
        generatePDF(data);

        return entity;
    }

    @Override
    protected void update(Ent entity, Hash data) throws Exception {
        entity.setIcon(data.getString("icon"));

        Boolean updateTable = false;

        if (data.getId() != 0) {
            if (!entity.getName().equals(data.getString("name"))) {
                String idx = updateName(entity, data);

                //renameTable(name);    -> ALTER TABLE exampletable RENAME TO new_table_name;
            }

            List<Field> persistentFields = systemFieldManager.listByEntity(entity.getId());
            List<Hash> fields = (List<Hash>) data.get("fields");

            List<Hash> renamedFields = fields.stream().filter(field -> field.getBoolean("updating", false)).collect(Collectors.toList());
            List<Hash> addedFields = fields.stream().filter(field -> field.getBoolean("creating", false)).collect(Collectors.toList());

            List<Hash> existentFields = fields.stream().filter(field -> !field.getBoolean("creating", false)).collect(Collectors.toList());
            List<String> deletedFields = persistentFields.stream()
                    .filter(field -> existentFields.stream().noneMatch(f -> f.getId() == field.getId()))
                    .map(Field::getName)
                    .collect(Collectors.toList());

            // renameFields(name, renamedFields);     -> ALTER TABLE Customer RENAME COLUMN Address TO Addr;
            // addFields(   name, addedFields  );     -> ALTER TABLE table_name DROP COLUMN column_name;
            // deleteFields(name, deletedFields);     -> ALTER TABLE table_name ALTER COLUMN column_name datatype;
        }
    }

    private String updateName(Ent entity, Hash hash) throws Exception {
        String name = hash.getString("name");
        String idx = SQLInjectionCleanner.clean(name);

        if (entityDAO.exist(entity.getApp().getId(), idx)) {
            throw new Exception("Entity with similar name already exist in this app");
        }

        entity.setIdx(idx);
        entity.setName(name);
        return idx;
    }

    @Override
    protected void afterSave(Ent entity, Hash data) throws Exception {
        List<Hash> fields = data.getHashList("fields");

        for (Hash field : fields) {
            //TODO fix here later
            boolean creating = true; //field.getBoolean("creating", Boolean.FALSE);
            boolean updating = field.getBoolean("updating", Boolean.FALSE);

//            if (creating) {
                field.put("entity", entity);
                field.remove("id");
//            }

//            if (updating) { //If is updating the entity
                field.put("idx", SQLInjectionCleanner.clean(field.getString("name")));
//            }

//            if (creating || updating) {
                System.out.println("field for save" + field);
                systemFieldManager.save(field);
//            } 
        }
    }

    private void generatePDF(Hash entity) {
        entity.put("lowercased_name", entity.getString("name"));
        Hash fileConfig = dvPdfGenerator.generate(entity);

        File file = IOUtil.createFile(fileConfig);

        System.out.println("SystemEntityManager -> generatePDF file.getAbsolutePath() = " + file.getAbsolutePath());
        System.out.println("SystemEntityManager -> generatePDF file.getName() = " + file.getName());

        IOUtil.writeFile(file, fileConfig.getString("src").trim());

        dvPdfGenerator.compileReportToFile(fileConfig, file);
    }

    public void deleteRecursivelly(String entityIdx) {
        roleEntityDAO.deleteByEntityIdx(entityIdx);
        entityDAO.deleteByEntityIdx(entityIdx);
    }

    public Hash load(RequestDTO request) {
        Integer entityId = request.getEntityId();

        Hash entity = new Hash(); // entityDAO.load(entityId);

        entity.put("views", viewDAO.listByEntity(entityId));
        entity.put("fields",fieldDAO.listByEntity(entityId));

        return entity;
    }
}
