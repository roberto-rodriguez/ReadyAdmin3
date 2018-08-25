/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dev.backEnd;

import com.system.dev.SourceGenerator;
import com.system.dto.request.Hash; 
import com.system.util.StringUtil;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author roberto.rodriguez
 */
@Component
public class ManagerGenerator extends BackEndGenerator {

    @Value("classpath:tpl/back/Manager.txt")
    private Resource template;
 

    public String additionalProcessing(String tpl, List<Hash> fields) throws IOException {  
         StringBuffer sb = new StringBuffer();

        for (Hash field : fields) {
            String fieldName = field.getString("name"); 
            String type = field.getString("type"); 
            String capitalizedName = StringUtil.capitalizeFirstLetter(fieldName); 

            sb.append("        entity.set" + capitalizedName + "(data.get" + type + "(\"" + fieldName + "\"));\n");
        }

        return tpl.replaceAll("%updates%", sb.toString());
    }
 

    protected String getSubPck() {
        return "manager";
    }

    protected String getFileSuffix() {
        return "Manager";
    }

    protected Resource getResource() {
        return template;
    }
}
