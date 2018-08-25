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
public class ControllerGenerator extends BackEndGenerator {

    @Value("classpath:tpl/back/Controller.txt")
    private Resource template;
 

    public String additionalProcessing(String tpl, List<Hash> fields) throws IOException {
        return tpl;
    }
 

    protected String getSubPck() {
        return "web";
    }

    protected String getFileSuffix() {
        return "Controller";
    }

    protected Resource getResource() {
        return template;
    }
}
