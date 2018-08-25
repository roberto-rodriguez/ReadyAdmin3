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

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rober
 */
@XmlRootElement
public class WebResponseData<T> extends WebResponse implements Serializable {

    private T data;

    /**
     * The default constructor
     */
    public WebResponseData() {
    }

    public WebResponseData(T data) {
        this.data = data;
    }

    public WebResponseData(int status, String statusMessage) {
        super(status, statusMessage);
    }

    public static WebResponseData toLoginFail() {
        return new WebResponseData(401, "Usuario o contraseña incorrectos");
    }

    public static WebResponseData forException(Exception e) {
        return new WebResponseData(500, e.getMessage());
    }

    /**
     *
     * @return
     */
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
