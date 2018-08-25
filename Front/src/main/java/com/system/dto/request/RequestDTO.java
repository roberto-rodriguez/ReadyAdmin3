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
package com.system.dto.request;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Criterion;

public class RequestDTO {  
    private String  appIdx;
    private Integer entityId;
    private String  entityIdx;

    private Integer page  = 0;
    private Integer start = 0;
    private Integer limit = 0;
    private String report = "";

    private List<Criterion> expressions = new ArrayList<>();

    public RequestDTO() {
    }

    public RequestDTO(String appIdx, Integer entityId, String entityIdx,
            Integer page, Integer start, Integer limit, String report,
            List<Criterion> expressions) {
        this.appIdx = appIdx;
        
        this.entityId = entityId;
        this.entityIdx = entityIdx; 
        
        this.page = page;
        this.start = start;
        this.limit = limit;
        this.report = report;
        this.expressions = expressions;

    }

    public RequestDTO(Integer entityId, String entityIdx, List<Criterion> expressions) {
        this.entityId = entityId;
        this.entityIdx = entityIdx;
        this.expressions = expressions;
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
     * @return the entityIdx
     */
    public String getEntityIdx() {
        return entityIdx;
    }

    /**
     * @param entityIdx the entityIdx to set
     */
    public void setEntityIdx(String entityIdx) {
        this.entityIdx = entityIdx;
    }

    /**
     * @return the page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * @return the start
     */
    public Integer getStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(Integer start) {
        this.start = start;
    }

    /**
     * @return the limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * @return the report
     */
    public String getReport() {
        return report;
    }

    /**
     * @param report the report to set
     */
    public void setReport(String report) {
        this.report = report;
    }

    /**
     * @return the expressions
     */
    public List<Criterion> getExpressions() {
        return expressions;
    }

    /**
     * @param expressions the expressions to set
     */
    public void setExpressions(List<Criterion> expressions) {
        this.expressions = expressions;
    }

    /**
     * @return the appIdx
     */
    public String getAppIdx() {
        return appIdx;
    }

    /**
     * @param appIdx the appIdx to set
     */
    public void setAppIdx(String appIdx) {
        this.appIdx = appIdx;
    }
}
