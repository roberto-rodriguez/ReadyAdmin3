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
import java.util.ArrayList;
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
public class DetailsGenerator extends FrontEndGenerator {

    @Value("classpath:tpl/front/Details.txt")
    private Resource template;
     
    @Autowired
    protected DevData devData;

    private static String TD_TPL = "                        '<td><b>%title%:</b> {%value%}</td>',\n";

    protected String getSubPck() {
        return "view";
    }

    public String additionalProcessing(String tpl, List<Hash> fields) throws IOException { 
        String fieldsCode = Lists.partition(fields, 3).stream()
                .map(tr -> "                   '<tr>',\n" + tr.stream().map(td -> buildTd(td)).collect(Collectors.joining()) + "                   '</tr>',")
                .collect(Collectors.joining());
        
        return tpl.replaceAll("%fields%", fieldsCode).replace("%uipck%", devData.getUiPck());
    }

    private static String buildTd(Hash fieldConfig) {
        String name = fieldConfig.getString("name");
        String type = fieldConfig.getString("type");
        String value = name;

        switch (type) {
            case "Double":
                value = "[Util.formatAmount(values." + name + ")]";
                break;
            case "Date":
                value = "[Util.formatDateTime(values." + name + ")]";
                break;
            case "Boolean":
                value = "[Util.formatBool(values." + name + ")]";
                break;
        }
        
        return TD_TPL.replaceAll("%title%", StringUtil.camelCaseToSpaceCapitalized(name))
                     .replaceAll("%value%", value);
    }

    protected String getFileSuffix() {
        return "Details";
    }

    protected Resource getResource() {
        return template;
    }
}
