/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dto;

import java.io.Serializable;

/**
 *
 * @author roberto.rodriguez
 */
public class DTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

}
