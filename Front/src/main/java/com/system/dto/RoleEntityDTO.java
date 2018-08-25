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
public class RoleEntityDTO extends DTO{
    
    private Integer id;
    private Boolean create;    
    private Boolean update;    
    private Boolean delete;
    private String role;
    private Integer roleId;
    private String entity;
    private Integer entityId;

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
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the roleId
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the create
     */
    public Boolean getCreate() {
        return create;
    }

    /**
     * @param create the create to set
     */
    public void setCreate(Boolean create) {
        this.create = create;
    }

    /**
     * @return the update
     */
    public Boolean getUpdate() {
        return update;
    }

    /**
     * @param update the update to set
     */
    public void setUpdate(Boolean update) {
        this.update = update;
    }

    /**
     * @return the delete
     */
    public Boolean getDelete() {
        return delete;
    }

    /**
     * @param delete the delete to set
     */
    public void setDelete(Boolean delete) {
        this.delete = delete;
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
}
