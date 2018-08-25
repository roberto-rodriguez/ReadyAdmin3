/*
 ** File: WebResponse.java
 **
 ** Date Created: December 2016
 **
 ** Copyright @ 2016-2018 Roberto Rodriguez.
 ** Email: robertoSoftwareEngineer@gmail.com
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Roberto Rodriguez.
 **
 */
package com.system.session;

import com.system.dto.request.Hash; 
import java.io.Serializable;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author rrodriguez
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DevData implements Serializable{
 
    private List<Hash> data;
    private String uiPck;
    private String entityName;
    
    public void update(Hash newData){
        for (Hash hash : data) {
            if(hash.getString("name").equals(newData.getString("name"))){
                hash.putAll(newData);
            }
        }
    }

    /**
     * @return the data
     */
    public List<Hash> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(List<Hash> data) {
        this.data = data;
    }

    /**
     * @return the uiPck
     */
    public String getUiPck() {
        return uiPck;
    }

    /**
     * @param uiPck the uiPck to set
     */
    public void setUiPck(String uiPck) {
        this.uiPck = uiPck;
    }

    /**
     * @return the entityName
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * @param entityName the entityName to set
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
  }
