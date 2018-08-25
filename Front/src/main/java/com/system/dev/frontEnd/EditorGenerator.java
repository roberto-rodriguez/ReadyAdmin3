/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dev.frontEnd;

import com.google.common.collect.Lists;
import com.system.dto.request.Hash;
import com.system.session.DevData;
import com.system.util.StringUtil;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author roberto.rodriguez
 */
@Component
public class EditorGenerator extends FrontEndGenerator {

    @Value("classpath:tpl/front/Editor.txt")
    private Resource template;
  
    @Autowired
    protected DevData devData;
    
    protected String getSubPck() {
        return "view";
    }

    private static String FIELD_TPL = "                        {\n"
            + "                            fieldLabel: '%title%',\n"
            + "                            name: '%name%',\n %xtype%"
            + "                        },\n";

    public String additionalProcessing(String tpl, List<Hash> fields) throws IOException {
        String str = Lists.partition(fields, 3).stream()
                .map(tr -> "                   { items: [ \n" + tr.stream().map(td -> buildField(td)).collect(Collectors.joining()) + "                   ] },\n")
                .collect(Collectors.joining());

        return tpl.replaceAll("%items%", str).replace("%uipck%", devData.getUiPck());
    }

    private static String buildField(Hash fieldConfig) {
        String name = fieldConfig.getString("name");
        String type = fieldConfig.getString("type");

        String xtype = "";

        switch (type) {
            case "Double":
            case "Date":
            case "Integer":
                xtype = "                           xtype: 'base" + type + "Field' \n";
                break; 
            case "Boolean":
                xtype = "                           xtype: 'baseCheckboxField' \n";
                break;
        }

        return FIELD_TPL.replaceAll("%title%", StringUtil.camelCaseToSpaceCapitalized(name))
                .replaceAll("%name%", name)
                .replaceAll("%xtype%", xtype);
    }

    protected String getFileSuffix() {
        return "Editor";
    }

    protected Resource getResource() {
        return template;
    }
}
