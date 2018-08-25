/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dev.backEnd;

import com.system.dev.SourceGenerator;
import com.system.dto.request.Hash; 

/**
 *
 * @author roberto.rodriguez
 */
public abstract class BackEndGenerator extends SourceGenerator{   
    
    protected abstract String getSubPck();
    
    protected abstract String getFileSuffix();
     
    protected String getFullPath(Hash request) {
        String path = request.getString("path");
        String pck = request.getString("pck");
        return path + "/Front/src/main/java/com/" + pck + "/" + getSubPck();
    }
    
    protected String getFileName(Hash request) {
        String fileName = request.getString("name");
        return fileName + getFileSuffix() + ".java";
    }
    
    protected String getFullName(Hash request) {
        String pck = request.getString("pck");
        return "com." + pck + "." + getSubPck() + "." + getFileName(request);
    }  
}
