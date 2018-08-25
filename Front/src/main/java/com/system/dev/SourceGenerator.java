/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dev;

import com.system.dto.request.Hash;
import com.system.util.IOUtil;
import com.system.util.StringUtil;
import java.io.IOException;
import java.util.List;
import org.springframework.core.io.Resource;

/**
 *
 * @author roberto.rodriguez
 */
public abstract class SourceGenerator {

    private static final String[] PLACEHOLDERS = new String[]{"name", "lowercased_name", "pck"};

    public Hash generate(Hash request) {
        Hash result = new Hash();
        try {
            String tpl = IOUtil.readResource(getResource());

            tpl = process(tpl, request);

            tpl = additionalProcessing(tpl, request.getHashList("fields"));

            result.put("name", getFileName(request));
            result.put("fullName", getFullName(request));
            result.put("fullPath", getFullPath(request));
            result.put("src", tpl);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public String process(String tpl, Hash request) {
        for (String placeholder : PLACEHOLDERS) {
            tpl = tpl.replaceAll("%" + placeholder + "%", request.getString(placeholder));
        }

        String title = request.containsKey("title") ? request.getString("title") : StringUtil.camelCaseToSpaceCapitalized(request.getString("name")) + "s";
        return tpl.replace("%title%", title);
    }

    protected String getFileSuffix() {
        return "";
    }

    ;
    
    protected String getSubPck() {
        return "";
    }

    ;

    protected abstract String additionalProcessing(String tpl, List<Hash> fields) throws IOException;

    protected abstract String getFullName(Hash request);

    protected abstract String getFullPath(Hash request);

    protected abstract String getFileName(Hash request);

    protected abstract Resource getResource();

}
