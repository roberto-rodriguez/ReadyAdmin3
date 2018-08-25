/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.web.mvc.controller;

import com.system.manager.ReportManager;
import com.system.reports.PDFView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Roberto
 */
@Controller
@RequestMapping(value = "/{appIdx}/{id}/{idx}/{reportType}/", method = RequestMethod.GET)
public class ReportController {

    @Autowired
    private ReportManager reportManager;

    @Autowired
    private ApplicationContext appContext;

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public ModelAndView viewReport(HttpServletRequest request,
            @PathVariable("appIdx") String appIdx,
            @PathVariable("id") Integer id,
            @PathVariable("idx") String idx,
            @PathVariable("reportType") String reportType,
            @RequestParam(value = "params", defaultValue = "") String params) {
        
        String url = request.getRequestURL().toString();

        if (url.contains("/AMS/")) {
            url = url.split("/AMS/")[0];
        }

        Map map = reportManager.getReport(url, appIdx, id, idx, reportType, params);

        if (reportType != null && reportType.equalsIgnoreCase("excel")) {
                        
            return new ModelAndView("ExcelView", "list", map);
        } else {
            Map<String, Object> parameterMap = new HashMap<>();
            parameterMap.put("datasource", new JRBeanCollectionDataSource((List)map.get("data")));

            PDFView pdfView = new PDFView();
            pdfView.createUrl(idx);
            pdfView.setApplicationContext(appContext);
            
            System.out.println("AMS pdfViewURL = " + pdfView.getUrl());

            return new ModelAndView(pdfView, parameterMap);
        }

    }

}
