/*
 ** File: WebResponse.java
 **
 ** Date Created: December 2016
 **
 ** Copyright @ 2016-2018 Roberto Rodriguez.
 ** Email: robertoSoftwareEngineer@gmail.com
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Roberto Rodriguez.
 **
 */
package com.system.web;

import com.system.dev.DevExecutor;
import com.system.dto.request.Hash;
import com.system.dto.response.WebResponseData;
import com.system.session.DevData;
import com.system.session.Principal;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Roberto Rodriguez
 */
@RestController
@RequestMapping(value = "/dev", method = RequestMethod.GET)
public class DevController {

    @Autowired
    protected DevExecutor devExecutor;

    @Autowired
    protected DevData devData;
    
    @Autowired
    protected Principal principal;

    @RequestMapping(value = "/ping")
    public @ResponseBody
    String ping() throws IOException {
        return "PING";
    }

    //"/var/lib/tomcat8/webapps/ROOT/WEB-INF/classes/"
    @RequestMapping(value = "/rep")
    public @ResponseBody
    String rep() throws IOException {
        URL location = DevController.class.getProtectionDomain().getCodeSource().getLocation(); 
        String full = location.getFile();
        String origin = full.substring(0, full.lastIndexOf("Front"));
        String amsOrigin = origin.replaceAll("Front", "AMS");
 
        String path = amsOrigin + "/AMS/WEB-INF/classes/reports/";

        File p = new File(path, "recibo.jasper");

//        return location.getFile();
        return path + " -> " + p.exists();
    }
    
    @RequestMapping(value = "/rep2")
    public @ResponseBody
    String rep2() throws IOException {
        URL location = DevController.class.getProtectionDomain().getCodeSource().getLocation();
       
        String full = location.getFile();
        String origin = full.substring(0, full.lastIndexOf("Front"));
        String amsOrigin = origin.replaceAll("Front", "AMS");
 
        String path = amsOrigin + "AMS/WEB-INF/classes/reports/";

        File p = new File(path, "recibo.jasper");

//        return location.getFile();
        return path + " -> " + p.exists();
    }

    @RequestMapping(value = "/tree")
    public @ResponseBody
    String tree() throws IOException {
        URL location = DevController.class.getProtectionDomain().getCodeSource().getLocation();

        String root = location.getFile().split("webapps")[0];

        File folder = new File(root + "webapps", "webapps");

        if (folder.exists()) {

            int indent = 0;
            StringBuilder sb = new StringBuilder();
            printDirectoryTree(folder, indent, sb);
            return sb.toString();

        } else {
            return "root not exist";
        }

    }

    private static void printDirectoryTree(File folder, int indent,
            StringBuilder sb) {
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("folder is not a Directory");
        }
        sb.append(getIndentString(indent));
        sb.append("+--");
        sb.append(folder.getName());
        sb.append("/");
        sb.append("\n");
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                printDirectoryTree(file, indent + 1, sb);
            } else {
                printFile(file, indent + 1, sb);
            }
        }

    }

    private static void printFile(File file, int indent, StringBuilder sb) {
        sb.append(getIndentString(indent));
        sb.append("+--");
        sb.append(file.getName());
        sb.append("\n");
    }

    private static String getIndentString(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append("|  ");
        }
        return sb.toString();
    }

    @RequestMapping(value = "/exist")
    public @ResponseBody
    String
            exist() throws IOException {
        URL location = DevController.class
                .getProtectionDomain().getCodeSource().getLocation();

        String root = location.getFile().split("ROOT")[0];

        return (new File(root, "ROOT")).exists() + "";

    }

    @RequestMapping(value = "/root")
    public @ResponseBody
    String
            root() throws IOException {
        URL location = DevController.class
                .getProtectionDomain().getCodeSource().getLocation();

        return location.getFile().split("ROOT")[0];

    }

    @RequestMapping(value = "/path")
    public @ResponseBody
    String
            path() throws IOException {
        URL location = DevController.class
                .getProtectionDomain().getCodeSource().getLocation();

        if (location == null) {
            return "Location is null";
        } else {
            return location.getFile();
        }

//        System.out.println("path = " + location.getFile());
//
//        String path = "";
//
//        if (location.getFile().contains("wtpwebapps")) { //This means the project was running using Eclipse
//            path = location.getFile().split("wtpwebapps")[0];
//        } else {
//            if (location.getFile().contains("webapps")) { //This means the project was running using Eclipse
//                path = location.getFile().split("webapps")[0];
//            } else {
//                throw new RuntimeException("Unable to find Web Apps path...");
//            }
//        }
//
//        return path + "webapps/";
    }

    @RequestMapping(value = "/generate", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    List<Hash> generate(@RequestBody Hash request) throws IOException {
        List<Hash> data = devExecutor.generate(request);
        devData.setData(data);
        return data;
    }

    @RequestMapping(value = "/preview")
    public @ResponseBody
    List<Hash> preview() throws IOException {
        return devData.getData();
    }

    @RequestMapping(value = "/write")
    public @ResponseBody
    String write() throws IOException {
        devExecutor.write();
        return "done";
    }

    @RequestMapping(value = "/revert")
    public @ResponseBody
    String revert() throws IOException {
        System.out.println("DevController.revert");
        devExecutor.revert();
        return "done";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = "application/json")
    public WebResponseData updateFile(@RequestBody Hash data) {

        System.out.println("updateFile");
        devData.update(data);

        return new WebResponseData();
    }

    @RequestMapping(value = "/uifolders")
    public List<String> getUIFolders() {
        return devExecutor.getUIFolders();
    }

//    public static void main(String[] args) {
//        File parent = new File("classpath:tpl/");
//
//        System.out.println("classpath:tpl/ .exists() = " + (new File("classpath:tpl/")).exists());
//        System.out.println("classpath:/tpl/ .exists() = " + (new File("classpath:/tpl/")).exists());
////        File file = new File("test_rober.txt");
//    }`
//    
    public static void main(String[] args) {
        String a = "/C:/Users/roberto.rodriguez/Documents/Ready/ReadyAdmin/Front/target/Front/WEB-INF/classes/com/system/web/DevController.class\"";
        System.out.println(a.split("Front")[0]);
        System.out.println(a.substring(0, a.indexOf("Front")));
        System.out.println(a.substring(0, a.lastIndexOf("Front")));
    }
}
