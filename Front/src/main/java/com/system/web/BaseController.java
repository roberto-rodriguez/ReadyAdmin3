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
import com.system.dto.request.RequestDTO;
import com.system.dto.response.NomenclatorDTO;
import com.system.manager.AbstractManager;
import com.system.util.beans.RequestParser;
import com.system.dto.response.WebResponse;
import com.system.dto.response.WebResponseData;
import com.system.dto.response.WebResponseDataList;
import com.system.manager.DVManager;
import com.system.session.LoginService;
import com.system.session.Principal;  
import com.system.util.StringUtil;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

@RestController
@RequestMapping(value = "/{id}/{idx}/")
public class BaseController {

    @Autowired
    protected LoginService loginService;

    @Autowired
    private RequestParser requestParser;

    @Autowired
    private DVManager dVManager;

    @Autowired
    protected Principal principal;

    @RequestMapping(value = "/report/{appIdx}/{reportType}", method = RequestMethod.GET)
    public Map report(
            @PathVariable("id") Integer id,
            @PathVariable("idx") String idx,
            @PathVariable("appIdx") String appIdx,
            @PathVariable("reportType") String reportType, //Type of report
            @RequestParam(value = "params", defaultValue = "") String params,
            HttpSession session) throws Exception {

        System.out.println("FrontAMS / report = " + reportType);
        System.out.println("FrontAMS / params = " + params);
        List<Criterion> data = requestParser.parseParamsToExpressions(params, id);
        RequestDTO request = new RequestDTO(appIdx, id, idx, 0, 0, 0, reportType, data);

        Map list = getManager(idx).report(request);
        return list;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map list(
            @PathVariable("id") Integer id,
            @PathVariable("idx") String idx,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "start", defaultValue = "0") Integer start,
            @RequestParam(value = "limit", defaultValue = "0") Integer limit,
            @RequestParam(value = "params", defaultValue = "") String params,
            HttpSession session) throws Exception {

        List<Criterion> data = requestParser.parseParamsToExpressions(params, id);
        RequestDTO requestDTO = new RequestDTO(principal.getAppIdx(), id, idx, page, start, limit, "", data);

        return getManager(idx).pageList(requestDTO);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = "application/json")
    public WebResponse save(
            @PathVariable("id") Integer id,
            @PathVariable("idx") String idx,
            @RequestBody Hash map,
            HttpSession session) throws ParseException {

        System.out.println("BaseController -> save :: map = " + map);

        try {
            return new WebResponseData(getManager(idx).save(id, idx, map));
        } catch (Exception e) {
            e.printStackTrace();
            return WebResponse.forException(e);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = "application/json")
    public WebResponse delete(
            @PathVariable("id") Integer id,
            @PathVariable("idx") String idx,
            @RequestBody Hash map,
            HttpSession session) throws ParseException, Exception {

        try {
            Hash data = new Hash();// RequestFormatter.formatRequestMap(map);
            return getManager(idx).delete(id, idx, data);

        } catch (Exception e) {
            e.printStackTrace();
            return WebResponse.forException(e);
        }
    }

    @RequestMapping(value = "/nomenclator", method = RequestMethod.GET)
    public WebResponseDataList nomenclator(
            @PathVariable("id") Integer id,
            @PathVariable("idx") String idx,
            @RequestParam(value = "params", defaultValue = "") String params,
            HttpSession session) throws Exception {

        List<Criterion> expressions = requestParser.parseParamsToExpressions(params, id);

        RequestDTO requestDTO = new RequestDTO(id, idx, expressions);
        List<NomenclatorDTO> list = getManager(idx).nomenclatorList(requestDTO);

        return new WebResponseDataList<NomenclatorDTO>(list);
    }

    @RequestMapping(value = "/load", method = RequestMethod.GET)
    public WebResponseData load(
            @PathVariable("id") Integer id, //For load, the id is data.id
            @PathVariable("idx") String idx,
            @RequestHeader("TOKEN") String token,
            HttpSession session,
            @RequestParam(value = "params", defaultValue = "") String params) throws Exception {

        if (principal.getToken() == null) {//When is first time login, we need 
            //to re-login to put values in the session
            WebResponseData result = loginService.login(session, new Hash("token", token), false);
            if (!result.isSuccess()) {
                return result;
            }
        }

        List<Criterion> expressions = requestParser.parseParamsToExpressions(params, id);

        RequestDTO requestDTO = new RequestDTO(id, idx, expressions);
        return new WebResponseData(getManager(idx).load(requestDTO));
    }

    private AbstractManager getManager(String entity) {
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();

        String entityName = StringUtil.capitalizeFirstLetter(entity);

        String app = principal.getAppIdx();

        String[] apps = new String[]{"system", app};

        for (String appName : apps) {
            String bean = appName + entityName + "Manager";

            if (context.containsBean(bean)) {
                return (AbstractManager) context.getBean(bean);
            }
        }
        //If it does not find the Manager, return the dvManager
        return dVManager;
    }
}
