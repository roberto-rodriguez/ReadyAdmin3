/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.model;

import java.util.Date;

/**
 *
 * @author roberto.rodriguez
 */
public class DVModel extends BaseModel { 

    /**
     * @return the created_by
     */
    public String getCreated_by() {
        return created_by;
    }

    /**
     * @param created_by the created_by to set
     */
    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    /**
     * @return the creation_date
     */
    public Date getCreation_date() {
        return creation_date;
    }

    /**
     * @param creation_date the creation_date to set
     */
    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }
    private String created_by;
    private Date creation_date;

    public DVModel() {
    }

    public DVModel(String created_by, Date creation_date, Integer id) {
        super(id);
        this.created_by = created_by;
        this.creation_date = creation_date;
    }

     
}
