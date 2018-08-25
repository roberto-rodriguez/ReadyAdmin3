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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired; 
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
@RequestMapping(value = "/auth", method = RequestMethod.GET)
public class AuthController extends HttpServlet {

    @Autowired
    protected LoginService loginService;
    @Autowired
    protected AccessService accessService;

    @Autowired
    public Principal principal;

    @RequestMapping(value = "/ping")
    public @ResponseBody
    String ping(HttpSession session) {
        return "PING";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public WebResponseData login(@RequestBody Hash data, HttpSession session) { 
        System.out.println("data = " + data); 
        try {
            return loginService.login(session, data, true); 
        } catch (Exception e) {
            e.printStackTrace();
            return WebResponseData.forException(e);
        } 
    }

//    @RequestMapping(value = "/checkAccess")
//    public WebResponseData checkAccess(@PathVariable("appId") String appId,
//            @PathVariable("pageId") String pageId, HttpSession session) {
//
////        Principal principal = (Principal) session.getAttribute(Principal.getPRINCIPAL());
//        return new WebResponseData(true);//accessService.checkAccess(principal,pageId)
//    }
}
