package com.system.reports;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

//@Component
public class PDFView extends JasperReportsPdfView { 
     
    
    @Override
    public void render(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.render(model, request, response);

//        String clientId = (String)request.getParameter("clientId");
        response.setHeader("Content-disposition", "attachment;filename=report.pdf");

        response.setContentType("application/pdf");
    }
    
    public void createUrl(String entity){
       setUrl("classpath:reports/" + entity+ ".jasper");  
    }
     

    public String getReportDataKey() {
        return "datasource";
    }

}
