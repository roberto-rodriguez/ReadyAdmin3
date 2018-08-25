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
package com.system.web;

import com.system.dto.request.Hash;
import com.system.session.Principal;
import com.system.dto.response.WebResponseData;
import com.system.session.AccessService;
import com.system.session.LoginService;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Roberto Rodriguez
 */
@RestController
@RequestMapping(value = "/session", method = RequestMethod.GET)
public class SessionController extends HttpServlet {

    @Autowired
    public Principal principal;
 

    @RequestMapping(value = "/setApp", method = RequestMethod.GET, consumes = "application/json")
    public WebResponseData setApp(
            @PathVariable("appId") Integer appId,
            @PathVariable("appIdx") String appIdx
    ) {

        principal.setAppId(appId);
        principal.setAppIdx(appIdx);

        return new WebResponseData();
    }

}
