/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 *
 * @author Alejo
 */
@Entity
@Table(name = "role_entity")
@XmlRootElement
public class RoleEntity extends BaseModel{ 
    @Id
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "serial")
    @SequenceGenerator(name = "pk_role_page", sequenceName = "role_page_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_role_page")
    @Generated(GenerationTime.INSERT)

    private Integer id;

    @Column(name = "_create")
    private Boolean create;

    @Column(name = "_update")
    private Boolean update;

    @Column(name = "_delete")
    private Boolean delete;

    @ManyToOne
    @JoinColumn(name = "rol")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "entity")
    private Ent entity;

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
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
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
    public Ent getEntity() {
        return entity;
    }

    /**
     * @param entity the entity to set
     */
    public void setEntity(Ent entity) {
        this.entity = entity;
    }

}
