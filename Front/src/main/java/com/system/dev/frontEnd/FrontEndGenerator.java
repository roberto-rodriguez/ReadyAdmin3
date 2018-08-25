/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dev.frontEnd;

import com.system.dev.SourceGenerator;
import com.system.dto.request.Hash; 
/**
 *
 * @author roberto.rodriguez
 */
public abstract class FrontEndGenerator extends SourceGenerator {

    protected String getSubPck() {
        return "";  //Re-define if needed
    } 

    protected abstract String getFileSuffix();

    protected String getFullPath(Hash request) {
        String path = request.getString("path");
        String uipck = request.getString("uipck");
        String lowercasedName = request.getString("lowercased_name");
        return path + "/AMS/src/main/webapp/app/" + uipck.replaceAll("\\.", "/") + "/" + lowercasedName + "/" + getSubPck();
    } 
    
    protected String getFileName(Hash request) {
        return request.getString("name") + getFileSuffix() + ".js";
    }

    protected String getFullName(Hash request) {
        String pck = request.getString("uipck"); 
        
        String lowercasedName = request.getString("lowercased_name");
        String subPack = getSubPck();

        String path = "Admin." + pck + "." + lowercasedName + ".";

        if (subPack != null && !subPack.isEmpty()) {
            path += subPack + ".";
        }

        return path + getFileName(request);  
    }
}
