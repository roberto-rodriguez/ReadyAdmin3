/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.model;

import com.system.model.*;
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
@Table(name = "entity")
@XmlRootElement
public class Ent extends BaseModel{ 
    @Id
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "serial")
    @SequenceGenerator(name = "pk_entity", sequenceName = "entity_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_entity")
    private Integer id;
    
    private String idx;

    @Column(name = "_name")
    private String name;
  
    @Column(name = "display_property")
    private String displayProperty; //This will me used to build the title of this specific entity

    @ManyToOne
    @JoinColumn(name = "app")
    private App app;

    @Type(type = "timestamp")
    @Column(name = "creation_date")
    private Date creationDate;
  
    @Column(name = "_order")
    private String order; //field1:asc|field2:desc
    
    @Column(name = "icon")
    private String icon;

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
     * @return the displayProperty
     */
    public String getDisplayProperty() {
        return displayProperty;
    }

    /**
     * @param displayProperty the displayProperty to set
     */
    public void setDisplayProperty(String displayProperty) {
        this.displayProperty = displayProperty;
    }

    /**
     * @return the app
     */
    public App getApp() {
        return app;
    }

    /**
     * @param app the app to set
     */
    public void setApp(App app) {
        this.app = app;
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
