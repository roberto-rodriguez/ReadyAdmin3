/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dev;

import com.dv.dev.SQLInjectionCleanner;
import com.system.dto.request.Hash;
import com.system.util.IOUtil;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author roberto.rodriguez
 */
@Component
@Qualifier("pdfGenerator")
public class PDFGenerator extends SourceGenerator {

    @Value("classpath:tpl/report/pdf.jrxml")
    private Resource template;

    private String HEADER_TPL = "			<staticText>\n"
            + "				<reportElement x=\"%x%\" y=\"20\" width=\"%width%\" height=\"20\" backcolor=\"#CC3300\" uuid=\"%uuid%\"/>\n"
            + "				<textElement textAlignment=\"Center\" verticalAlignment=\"Middle\">\n"
            + "					<font size=\"12\" isBold=\"false\"/>\n"
            + "				</textElement>\n"
            + "				<text><![CDATA[%name%]]></text>\n"
            + "			</staticText>\n";

    private String BAND_TPL = "			<textField%parameters%>\n"
            + "				<reportElement x=\"%x%\" y=\"0\" width=\"%width%\" height=\"20\" uuid=\"%uuid%\"/>\n"
            + "				<textElement textAlignment=\"Center\" verticalAlignment=\"Middle\"/>\n"
            + "				<textFieldExpression><![CDATA[%name%]]></textFieldExpression>\n"
            + "			</textField>\n";

    public String additionalProcessing(String tpl, List<Hash> fields) throws IOException {
        tpl = processFields(tpl, fields);
        tpl = processHeader(tpl, fields);
        return processBand(tpl, fields);
    }

    public String processFields(String tpl, List<Hash> fields) {
        StringBuilder sb = new StringBuilder();
        for (Hash field : fields) {
            String type = "String";

            switch (field.getString("type")) {
                case "Date":
                    type = "Long";
                    break;
                case "Double":
                    type = "Double";
                    break;
            }

            sb.append("<field name=\"" + field.getString("name") + "\" class=\"java.lang." + type + "\"/>\n");
        }
        return tpl.replace("%fields%", sb.toString());
    }

    public String processHeader(String tpl, List<Hash> fields) { 
        return process(tpl, HEADER_TPL, fields, "%header%", (field, template) -> template.replace("%name%", field.getString("name")));
    }

    public String processBand(String tpl, List<Hash> fields) {
        return process(tpl, BAND_TPL, fields, "%band%", (field, template) -> {
            String parameters = "";
            String name = "$F{" + field.getString("name") + "}";
            switch (field.getString("type")) {
                case "Date":
                    name = "new java.util.Date($F{" + field.getString("name") + "})";
                    parameters = " isStretchWithOverflow=\"true\" pattern=\"MM/dd/yyyy\"";
                    break;
                case "Double":
                    parameters = " pattern=\"##0.00\"";
            }

            return template.replace("%name%", name).replace("%parameters%", parameters);
        });
    }

    public String process(String tpl, String fieldTpl, List<Hash> fields, String placeholder, BiFunction<Hash, String, String> processor) {
        StringBuilder sb = new StringBuilder();
        Integer width = 555 / fields.size();
        Integer x = 0;

        for (int i = 0; i < fields.size(); i++) {
            int extraWidth = i < 555 % fields.size() ? 1 : 0;

            String str = fieldTpl.replace("%uuid%", UUID.randomUUID().toString());
            str = str.replace("%x%", x.toString());
            str = str.replace("%width%", (width + extraWidth) + "");

            str = processor.apply(fields.get(i), str);
            x += width + extraWidth;
            sb.append(str);
        }

        return tpl.replace(placeholder, sb.toString());
    }

    protected String getFullPath(Hash request) {
        String path = IOUtil.getAmsPath();
        System.out.println("AMS_PATH = " + path);
        return path + "/AMS/src/main/resources/reports/";
    }

    protected String getFileName(Hash request) {
        String lowercasedName = request.getString("lowercased_name");
        return lowercasedName + ".jrxml";
    }

    protected String getFullName(Hash request) {
        return getFileName(request);
    }

    @Override
    protected Resource getResource() {
        return template;
    }

    public void compileReportToFile(Hash fileConfig, File file) {
        try {
            Thread.sleep(1000);

            final JasperDesign design = JRXmlLoader.load(file);
            String path = fileConfig.getString("fullPath");
            String name = fileConfig.getString("name").split("jrxml")[0] + "jasper";

            JasperCompileManager.compileReportToFile(design, path + name);
        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
