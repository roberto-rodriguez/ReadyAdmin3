/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.reports;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 *
 * @author Alejo
 */
public class ExcelBuilder extends AbstractExcelView{
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

    @Override
    protected void buildExcelDocument(Map<String, Object> map, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
              
      // get data model which is passed by the Spring container
        List<Map> data = (List<Map>) ((Map)map.get("list")).get("data");
        List<Map> fields = (List<Map>) ((Map)map.get("list")).get("fields");
        String entity = (String) map.get("idx");        

       // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet(StringUtils.capitalize(entity));
        sheet.setDefaultColumnWidth(30);   
        
         // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.DARK_BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        createSheetHeader(sheet, fields, style);
              
        int rowCount = 1;            
        for (Map element : data) {
            HSSFRow aRow = sheet.createRow(rowCount++);            
            setRowData(aRow, element, fields);
        }

    }
    
    private void createSheetHeader(HSSFSheet sheet, List<Map> fields, CellStyle style){
      
       HSSFRow header = sheet.createRow(0);       
       int countCell = 0;        
        for (Map element : fields) {
          header.createCell(countCell).setCellValue((String)element.get("name"));
          header.getCell(countCell).setCellStyle(style);         
          countCell++;
        }
    }
    
    private void setRowData(HSSFRow row, Map rowData, List<Map> colums){
        int countCell = 0;  
        
        for (Map colum : colums) {
            String type = (String)colum.get("type");
            String key = (String) colum.get("idx");
            
            switch (type){
                case "Double":
                    row.createCell(countCell++).setCellValue((Double)rowData.get(key));
                    break;
                case "Integer":
                    row.createCell(countCell++).setCellValue((Integer)rowData.get(key));
                    break;
                case "Date":
                    row.createCell(countCell++).setCellValue(sdf.format(new Date(Long.parseLong(rowData.get(key).toString() )) )+ "");
                    break;
                case "Boolean":
                    row.createCell(countCell++).setCellValue( ((Boolean)rowData.get(key))==true? "Yes":"No");
                    break;
                default:
                     row.createCell(countCell++).setCellValue((String)rowData.get(key));
                     break;           
            }
            
        }
        
    }
    
     
}
