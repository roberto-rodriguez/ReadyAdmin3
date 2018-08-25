/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dv.dev;

import com.system.dev.*;
import com.dv.dev.SQLInjectionCleanner;
import com.system.dto.request.Hash;
import com.system.util.IOUtil;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author roberto.rodriguez
 */
@Component
@Qualifier("dvPdfGenerator")
public class DvPdfGenerator extends PDFGenerator{
 
    public String additionalProcessing(String tpl, List<Hash> fields) throws IOException {
        //When generating PDF from DV
        //Esto ya se hizo en el SystemEntityManager por eso lo comento
        //Sin probar
        
//        for (Hash field : fields) {
//            if (!field.containsKey("name") && field.containsKey("label")) {
//                field.put("name", SQLInjectionCleanner.clean(field.getString("label")));
//            }
//        } 
        return super.additionalProcessing(tpl, fields);
    }
 
    protected String getFullPath(Hash request) {
        String path = IOUtil.getAmsPath();
        System.out.println("AMS_PATH = " + path);
        return path + "/AMS/WEB-INF/classes/reports/";
    }
 
}
