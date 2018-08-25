/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.model;

import java.util.Date;
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
import org.hibernate.annotations.Type;

@Entity
@Table(name = "views")
@XmlRootElement
public class View extends BaseModel {

    @Id
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "serial")
    @SequenceGenerator(name = "pk_views", sequenceName = "views_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_views")
    private Integer id;

    private String idx;

    @Column(name = "_name")
    private String name;

    @Column(name = "xtype")
    private String xtype;

    private String filters;

    @Column(name = "_order")
    private String order;

    private String config;

    private Boolean menu;

    @Type(type = "timestamp")
    @Column(name = "creation_date")
    private Date creationDate;

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
     * @return the xtype
     */
    public String getXtype() {
        return xtype;
    }

    /**
     * @param xtype the xtype to set
     */
    public void setXtype(String xtype) {
        this.xtype = xtype;
    }

    /**
     * @return the filters
     */
    public String getFilters() {
        return filters;
    }

    /**
     * @param filters the filters to set
     */
    public void setFilters(String filters) {
        this.filters = filters;
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
     * @return the config
     */
    public String getConfig() {
        return config;
    }

    /**
     * @param config the config to set
     */
    public void setConfig(String config) {
        this.config = config;
    }

    /**
     * @return the menu
     */
    public Boolean getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(Boolean menu) {
        this.menu = menu;
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

    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
