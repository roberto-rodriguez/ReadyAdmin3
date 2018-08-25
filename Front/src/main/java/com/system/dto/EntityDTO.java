/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dto;

/**
 *
 * @author Alejo
 */
public class EntityDTO extends DTO{ 
    
    private Integer id;
    
    private String idx;

    private String name;
    
    private String icon;

    /**
     * @return the id
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
 
    /**
     * @return the idx
     */
    public String getIdx() {
        return idx;
    }

    /**
     * @param idx the idx to set
     */
    public void setIdx(String idx) {
        this.idx = idx;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
}
