/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dev.backEnd;
 
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
public class ModelGenerator extends BackEndGenerator {

    @Value("classpath:tpl/back/Model.txt")
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

            if (type.equalsIgnoreCase("Date")) {
                sb.append("\n");
                sb.append("     @Type(type = \"timestamp\")"); 
            }

            sb.append("\n");
            sb.append("     @Column(name = \"" + StringUtil.camelCaseToUnderscore(fieldName) + "\")");
            sb.append("\n");
            sb.append("     private " + type + " " + fieldName + ";\n");
            sb.append("\n");
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
        return "model";
    }
    
    protected String getFileSuffix() {
        return "";
    }

    protected Resource getResource() {
        return template;
    }
}
