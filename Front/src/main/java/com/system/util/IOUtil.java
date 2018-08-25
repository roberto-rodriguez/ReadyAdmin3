/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.util;

import com.system.dto.request.Hash;
import com.system.web.DevController;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import org.springframework.core.io.Resource;

/**
 *
 * @author roberto.rodriguez
 */
public class IOUtil {

    public static String readResource(Resource tpl) throws IOException {
        StringBuffer sb = new StringBuffer();

        try (DataInputStream in = new DataInputStream(tpl.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

            String strLine;
            while ((strLine = br.readLine()) != null) {
                sb.append(" " + strLine + "\n");
            }
        }
        return sb.toString();
    }

    public static void writeFile(File file, String str) {
        try (PrintWriter pw = new PrintWriter(file)) {
            String[] lines = str.split("\n");

            for (int i = 0; i < lines.length; i++) {
                pw.println(lines[i]);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static File createFile(Hash fileConfig) {
        String fullPath = fileConfig.getString("fullPath");
        String fullName = fileConfig.getString("name");

        File parentFolder = new File(fullPath);

        if (!parentFolder.exists()) {
            parentFolder.mkdirs();
        }

        File file = new File(parentFolder, fullName);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Failed to create file " + fullName + " in path " + fullPath);
                e.printStackTrace();
            }

        }

        return file;
    }

    public static void deleteRecursively(String path) throws IOException {
        Path directory = Paths.get(path);
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static String getAmsPath() {
        URL location = IOUtil.class.getProtectionDomain().getCodeSource().getLocation();
        String full = location.getFile();
        String origin = full.substring(0, full.lastIndexOf("Front"));
        return origin.replaceAll("Front", "AMS");
    }
}
