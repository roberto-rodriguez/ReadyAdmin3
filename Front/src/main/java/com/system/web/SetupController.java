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

import com.dv.setup.SetupDv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Roberto Rodriguez
 */
@RestController
@RequestMapping(value = "/setup", method = RequestMethod.GET)
public class SetupController {

    @Autowired
    private SetupDv setupDv;

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping() {
        System.out.println("Setup---ping");
        return "ping";
    }
    
    @RequestMapping(value = "/dv", method = RequestMethod.GET)
    public String setupDv() {
        System.out.println("Setup---dv");
        setupDv.setup();

        return "DONE";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String setupAll() {
        System.out.println("Setup---all");
        setupDv.setup();
        return "DONE";
    }

}
