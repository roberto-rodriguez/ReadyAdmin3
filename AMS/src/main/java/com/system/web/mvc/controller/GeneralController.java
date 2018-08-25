/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.web.mvc.controller;

import com.system.manager.AuthManager;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Roberto Rodriguez
 */
@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class GeneralController {

    @Autowired
    private AuthManager authManager;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, @CookieValue(value = "RA_T0k3n", required = false) String token) {
        System.out.println("index... token = " + token);

        String sessionData = null;

        if (token != null && !token.isEmpty()) {
            System.out.println("Calling autoLogin...");
            sessionData = authManager.autoLogin(request.getRequestURL().toString(), token);
        }

        return new ModelAndView("index", "sessionData", sessionData);
    }

}
