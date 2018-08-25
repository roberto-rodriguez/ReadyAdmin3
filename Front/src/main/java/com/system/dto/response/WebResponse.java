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

import static com.system.util.Constants.RESULT_CODE_SUCCESS;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rober
 */
@XmlRootElement
public class WebResponse implements Serializable {

    /**
     *
     */
    private int status;
    /**
     *
     */
    private String statusMessage;

    /**
     * The default constructor
     */
    public WebResponse() {
        this.status = RESULT_CODE_SUCCESS;
        this.statusMessage = "SUCCESS";
    }

    public WebResponse(int status, String statusMessage) {
        this.status = status;
        this.statusMessage = statusMessage;
    }

    public Boolean isSuccess() {
        return this.status == RESULT_CODE_SUCCESS;
    }

    public static WebResponse forException(Exception e) {
        return new WebResponse(500, e.getMessage());
    }

    /**
     *
     * @return
     */
    public int getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     *
     * @param statusMessage
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

}
