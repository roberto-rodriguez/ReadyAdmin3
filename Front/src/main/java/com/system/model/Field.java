/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.model;

import com.system.dto.request.Hash;
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

@Entity
@Table(name = "field")
@XmlRootElement
public class Field extends BaseModel{  
    @Id
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "serial")
    @SequenceGenerator(name = "pk_field", sequenceName = "field_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_field")
    private Integer id;
    
    private String idx;

    @Column(name = "_name")
    private String name; 

    @Column(name = "_type")
    private String type;
 
    private String ref;

    private Boolean grid;
    private Boolean readonly; // id, createdBy and creationDate will be read only fields included in all entities

    @ManyToOne
    @JoinColumn(name = "entity")
    private Ent entity; 
 
    public static Hash buildIdField(){
        return new Hash("idx", "idx", "name", "Id", "type", "Integer");
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
