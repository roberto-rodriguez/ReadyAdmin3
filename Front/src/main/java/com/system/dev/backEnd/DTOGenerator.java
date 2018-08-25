/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dev.backEnd;

import com.system.dev.SourceGenerator;
import com.system.dto.request.Hash;
import com.system.util.IOUtil;
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
public class DTOGenerator extends BackEndGenerator {

    @Value("classpath:tpl/back/DTO.txt")
    private Resource template;

    @Value("classpath:tpl/back/GettersSetters.txt")
    private Resource gettersSetters;

    public String additionalProcessing(String tpl, List<Hash> fields) throws IOException {  
        tpl = processFields(tpl, fields);
        tpl = gettersAndSetters(tpl, fields);
        return tpl;
    }

    public String processFields(String tpl, List<Hash> fields) {
        StringBuffer sb = new StringBuffer();

        for (Hash field : fields) {
            String fieldName = field.getString("name");
            String type = field.getString("type");

            sb.append("     private " + type + " " + fieldName + ";\n");
        }

        return tpl.replaceAll("%fields%", sb.toString());
    }

    public String gettersAndSetters(String tpl, List<Hash> fields) throws IOException {
        String getterSetterTpl = IOUtil.readResource(gettersSetters);

        StringBuilder sb = new StringBuilder();

        for (Hash field : fields) {
            String gettersSetters = getterSetterTpl;
            String fieldName = field.getString("name");
            String type = field.getString("type");
            String uppercaseName = StringUtil.capitalizeFirstLetter(fieldName);

            gettersSetters = gettersSetters
                    .replaceAll("%name%", fieldName)
                    .replaceAll("%type%", type)
                    .replaceAll("%capitalized_name%", uppercaseName);
            sb.append(gettersSetters);
        }

        return tpl.replaceAll("%getters_setters%", sb.toString());
    }

    protected String getSubPck() {
        return "dto";
    }

    protected String getFileSuffix() {
        return "DTO";
    }

    protected Resource getResource() {
        return template;
    }
}
