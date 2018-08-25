/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dev.frontEnd;

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
public class GridGenerator extends FrontEndGenerator {

    @Value("classpath:tpl/front/Grid.txt")
    private Resource template; 
    
    @Autowired
    protected DevData devData;

    protected String getSubPck() {
        return "grid";
    }

    private static String COLUMN_TPL = "            {\n"
            + "                text: '%title%',\n"
            + "                dataIndex: '%name%' \n %filter% %xtype%"
            + "            },\n";

    public String additionalProcessing(String tpl, List<Hash> fields) throws IOException {
        List<Hash> gridFields = fields.stream()
                .filter(f -> f.getBoolean("includeGrid"))
                .collect(Collectors.toList());

        tpl = tpl.replaceAll("%default_width%", StringUtil.formatDouble(99D / gridFields.size()));

        String fieldsHTML = gridFields.stream().map(f -> {
            String name = f.getString("name");

            return COLUMN_TPL
                    .replaceAll("%title%", StringUtil.camelCaseToSpaceCapitalized(name))
                    .replaceAll("%name%", name)
                    .replaceAll("%filter%", getFilter(f))
                    .replaceAll("%xtype%", getXType(f));
        }).collect(Collectors.joining());

        return tpl.replace("%items%", fieldsHTML).replace("%uipck%", devData.getUiPck());
    }

    private static String getFilter(Hash field) {
        String type = field.getString("type");
        switch (type) {
            case "Boolean":
            case "Date":
            case "Integer":
            case "Double":
                return "              ,filterType: 'filter" + type + "'\n";
            default:
                return "";
        }
    }

    private static String getXType(Hash field) {
        String type = field.getString("type");
        switch (type) {
            case "Boolean":
            case "Date": 
                return "              ,xtype: '" + type.toLowerCase() + "GridColumn'\n";
            case "Double": 
                return "              ,xtype: 'amountGridColumn'\n";
            default:
                return "";
        }
    }

    protected String getFileSuffix() {
        return "Grid";
    }

    protected Resource getResource() {
        return template;
    }
}
