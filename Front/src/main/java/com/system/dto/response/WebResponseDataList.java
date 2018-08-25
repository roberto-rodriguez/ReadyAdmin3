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

package com.system.dto.response;

import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Roberto Rodriguez   :: <roberto.rodriguez@smartbt.com>
 */
@XmlRootElement
public class WebResponseDataList<T> extends WebResponse {

    private Collection<T> data; 
    public WebResponseDataList() {
    }

    public WebResponseDataList(Collection<T> data) {
        super();
        this.data = data; 
    }

    public WebResponseDataList(Collection<T> data, String label) {
        super();
        this.data = data;  
    }
    public WebResponseDataList(Collection<T> data, Integer totalPages) {
        this(data); 
    }

    /**
     *
     * @return
     */
    public Collection<T> getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(Collection<T> data) {
        this.data = data;
    } 
}