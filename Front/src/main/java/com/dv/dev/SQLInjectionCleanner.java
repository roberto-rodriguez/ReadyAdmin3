/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dv.dev;

/**
 *
 * @author roberto.rodriguez
 */
public class SQLInjectionCleanner {

    public static void main(String[] args) {
        System.out.println(" La Casa; 'de 23_34 (* ".trim().toLowerCase().replaceAll(" ", "_").replaceAll("[^A-Za-z0-9_]", ""));
    }

    //Method that clean SQL Injection
    public static String clean(String x) {
//        if (x == null || x.isEmpty()) {  TODO uncomment this
//            return "";
//        }

        StringBuilder sBuilder = new StringBuilder(x.length() * 11 / 10);

        x = x.trim().toLowerCase().replaceAll(" ", "_").replaceAll("[^A-Za-z0-9_]", "");

        int stringLength = x.length();

        for (int i = 0; i < stringLength; ++i) {
            char c = x.charAt(i);

            switch (c) {
                case 0:
                    /* Must be escaped for 'mysql' */
                    sBuilder.append('\\');
                    sBuilder.append('0');
                    break;
                case '\n':
                    /* Must be escaped for logs */
                    sBuilder.append('\\');
                    sBuilder.append('n');
                    break;
                case '\r':
                    sBuilder.append('\\');
                    sBuilder.append('r');
                    break;
                case '\\':
                    sBuilder.append('\\');
                    sBuilder.append('\\');
                    break;
                case '\'':
                    sBuilder.append('\\');
                    sBuilder.append('\'');
                    break;
                case '"':
                    sBuilder.append('\\');
                    sBuilder.append('"');
                    break;
                case '\032':
                    /* This gives problems on Win32 */
                    sBuilder.append('\\');
                    sBuilder.append('Z');
                    break;
                case '\u00a5':
                case '\u20a9':
                case ';':
                // escape characters interpreted as backslash by mysql
                // fall through 
                default:
                    sBuilder.append(c);
            }
        }

        return sBuilder.toString();
    }
}
