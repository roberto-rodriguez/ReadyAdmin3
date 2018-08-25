/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dto;

import com.system.model.*;

public class FieldDTO extends BaseModel {

    private Integer id;

    private String idx;

    private String name;

    private String type;

    private String ref;

    private Boolean grid;
    private Boolean readonly;

    private String entity;
    private Integer entityId;

    private String order; //ASC, DESC or null

    @Override
    public String toString() {
        return "< " + idx + ": " + type + " ( " + getRef() + " )>"; //To change body of generated methods, choose Tools | Templates.
    }

    public FieldDTO() {
    }

    public FieldDTO(String idx, String ref) {
        this.idx = idx;
        this.ref = ref;
    }

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

    /**
     * @return the order
     */
    public String getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(String order) {
        this.order = order;
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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the grid
     */
    public Boolean getGrid() {
        return grid;
    }

    /**
     * @param grid the grid to set
     */
    public void setGrid(Boolean grid) {
        this.grid = grid;
    }

    /**
     * @return the entity
     */
    public String getEntity() {
        return entity;
    }

    /**
     * @param entity the entity to set
     */
    public void setEntity(String entity) {
        this.entity = entity;
    }

    /**
     * @return the entityId
     */
    public Integer getEntityId() {
        return entityId;
    }

    /**
     * @param entityId the entityId to set
     */
    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
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
     * @return the readonly
     */
    public Boolean getReadonly() {
        return readonly;
    }

    /**
     * @param readonly the readonly to set
     */
    public void setReadonly(Boolean readonly) {
        this.readonly = readonly;
    }

    /**
     * @return the ref
     */
    public String getRef() {
        return ref;
    }

    /**
     * @param ref the ref to set
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

}
