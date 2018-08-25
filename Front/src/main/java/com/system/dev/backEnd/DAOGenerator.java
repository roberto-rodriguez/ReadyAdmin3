/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dev.backEnd;
 
import com.system.dto.request.Hash; 
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
public class DAOGenerator extends BackEndGenerator {

    @Value("classpath:tpl/back/DAO.txt")
    private Resource template;
 

    public String additionalProcessing(String tpl, List<Hash> fields) throws IOException {  
         StringBuffer sb = new StringBuffer();

        for (Hash field : fields) {
            String fieldName = field.getString("name"); 

            sb.append("        .add(Projections.property(\"" + fieldName + "\").as(\"" + fieldName + "\"))\n");
        }

        return tpl.replaceAll("%projections%", sb.toString() + ";");
    }
 

    protected String getSubPck() {
        return "dao";
    }

    protected String getFileSuffix() {
        return "DAO";
    }

    protected Resource getResource() {
        return template;
    }
}
