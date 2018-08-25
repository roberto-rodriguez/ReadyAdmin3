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

import com.system.dto.UserAppDTO;
import com.system.dto.UserDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author rrodriguez
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Principal implements Serializable {

    public static String PRINCIPAL = "PRINCIPAL";
    public static String TOKEN = "TOKEN";

    private Integer userId;
    private Integer appId = 0; //Current App Id (0 when user is in the Home View) 
    private String appIdx; //Current App Idx (null when user is in the Home View) 
    private Integer roleId; //1-Super Admin   2-Admin   3-Collaborator  > Custom Roles
    private String token;
    private String name;

    private List<UserAppDTO> userApps = new ArrayList<>();

    public void init(UserDTO user) {
        setUserId(user.getId());
        setToken(user.getToken());
        setName(user.getFirstName() + " " + user.getLastName());
    }

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the userApps
     */
    public List<UserAppDTO> getUserApps() {
        return userApps;
    }

    /**
     * @param userApps the userApps to set
     */
    public void setUserApps(List<UserAppDTO> userApps) {
        this.userApps = userApps;
    }

    /**
     * @return the appId
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * @param appId the appId to set
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * @return the roleId
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
