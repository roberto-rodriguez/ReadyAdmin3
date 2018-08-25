/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.util;

import com.google.common.base.CaseFormat;
import java.text.DecimalFormat;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author roberto.rodriguez
 */
public class StringUtil {

    private static final DecimalFormat df2 = new DecimalFormat(".##");

    public static String formatDouble(Double d) {
        return df2.format(d);
    }

    public static String maskNumber(String str) {
        if (str == null) {
            return null;
        }

        int end = str.length() - 4;
        String overlay = StringUtils.repeat("*", end);

        return StringUtils.overlay(str, overlay, 0, end);
    }

    public static String lowercaseFirstLetter(String str) {
        if (str == null) {
            return null;
        }
        return String.valueOf(str.charAt(0)).toLowerCase() + str.substring(1);
    }

    public static String capitalizeFirstLetter(String str) {
        if (str == null) {
            return null;
        }
        return String.valueOf(str.charAt(0)).toUpperCase() + str.substring(1);
    }

    public static String camelCaseToUnderscore(String str) {
        if (str == null) {
            return null;
        }
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str);
    }

    public static String camelCaseToSpaceCapitalized(String str) {
        if (str == null) {
            return null;
        }
        return Stream.of(StringUtils.splitByCharacterTypeCamelCase(str))
                .map(w -> capitalizeFirstLetter(w))
                .collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        System.out.println(camelCaseToSpaceCapitalized("firstName"));

    }

    public static String getMaskedNumber(String cardNumber) {
        String maskedNumber = "";
        if (cardNumber != null && cardNumber.length() >= 4) {
            maskedNumber += cardNumber.substring(0, 4);

            if (cardNumber.length() >= 8) {
                maskedNumber += "********";
                maskedNumber += cardNumber.substring(cardNumber.length() - 4);
            }
        }
        return maskedNumber;
    }

    public static boolean equalAmounts(Double a, Double b) {
        return (a != null) && (b != null) && (Math.abs(a - b) < 0.01);
    }
}
