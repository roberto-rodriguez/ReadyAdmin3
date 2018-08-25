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
package com.system.util.beans;

import com.dv.criteria.DVQueryBuilder;
import com.dv.criteria.HibernateQueryBuilder;
import com.dv.criteria.QueryBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rrodriguez
 */
@Service
public class RequestParser {

    public static final String PARAMS_SEPARATOR = "@and@";//"@@";
    public static final String PARAMS_EQUAL = "@is@";

    @Autowired
    private DVQueryBuilder dvQueryBuilder;

    @Autowired
    private HibernateQueryBuilder hibernateQueryBuilder;

    public List<Criterion> parseParamsToExpressions(String paramsStr, Integer id) {
        System.out.println("RequestParser -> parseParamsToExpressions:: id = " + id);
        System.out.println("RequestParser -> parseParamsToExpressions:: params = " + paramsStr);
        List<Criterion> expressions = new ArrayList<>();

        if (paramsStr == null || paramsStr.isEmpty()) {
            return expressions;
        }

        QueryBuilder queryBuilder = null;

        if (id == 0) {
            queryBuilder = hibernateQueryBuilder;
        } else {
            queryBuilder = dvQueryBuilder;
        }

        paramsStr = paramsStr.replaceAll("%20", " ");
        System.out.println("RequestParser -> parseParamsToExpressions:: paramsClean = " + paramsStr);
        if (paramsStr != null && !paramsStr.isEmpty()) {
            String[] prefixValues = paramsStr.split(PARAMS_SEPARATOR);
            System.out.println("RequestParser -> parseParamsToExpressions:: prefixValues = " + Arrays.toString(prefixValues));
            for (String prefixValue : prefixValues) {
                if (prefixValue.length() > 0 && prefixValue.contains(PARAMS_EQUAL)) {
                    String[] kv = prefixValue.split(PARAMS_EQUAL);

                    String key = kv[0];
                    String value = kv[1];
                    System.out.println("RequestParser -> parseParamsToExpressions:: key = " + key);
                    System.out.println("RequestParser -> parseParamsToExpressions:: value = " + value);
                    Criterion expression = paramToExpression(key, value, queryBuilder);

                    if (expression != null) {
                        expressions.add(expression);
                    }
                }
            }
        }
        return expressions;
    }

    private static Criterion paramToExpression(String key, String value, QueryBuilder queryBuilder) {
        Criterion criterion = null;

        try {
            String prefix = key;

            if (value.trim().startsWith("(") && value.contains(")")) {
                prefix = value.trim().substring(0, value.indexOf(")") + 1);
                value = value.trim().substring(value.indexOf(")") + 1);
            }

            if (value.isEmpty() || value.equalsIgnoreCase("null")) {
                return null;
            }

            switch (prefix) {
                case "(S)":
                    criterion = parseString(key, value, queryBuilder);
                    break;
                case "(I)":
                case "(L)":
                case "(d)":
                    criterion = parseNumber(key, value, prefix, queryBuilder);
                    break;
                case "(D)":
                    criterion = parseDate(key, value, queryBuilder);
                    break;
                case "(B)":
                    criterion = queryBuilder.eq(key, Boolean.parseBoolean(value));
                    break;
                default:
                    criterion = queryBuilder.eq(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return criterion;
    }

    private static Criterion parseString(String key, String valueStr, QueryBuilder queryBuilder) {
        Criterion criterion = null;
        MatchMode matchMode = MatchMode.ANYWHERE;

        if (valueStr.trim().startsWith("[")) {
            String operator = valueStr.trim().substring(1, valueStr.indexOf("]"));
            valueStr = valueStr.trim().substring(valueStr.indexOf("]") + 1);

            switch (operator) {
                case "start":
                    matchMode = MatchMode.START;
                    break;
                case "end":
                    matchMode = MatchMode.END;
                    break;
                case "exact":
                    matchMode = MatchMode.EXACT;
                    break;
                case "ne":
                    return queryBuilder.not(queryBuilder.ilike(key, valueStr, MatchMode.EXACT));
            }
        }

        if (key.contains(",")) { //This is an special case, to seatch for firstName,lastName (Not used in Dv)
            final String val = valueStr;
            List<Criterion> subExpressions = Stream.of(key.split(","))
                    .map(s -> queryBuilder.ilike(s, val, MatchMode.ANYWHERE))
                    .collect(Collectors.toList());

            criterion = queryBuilder.or(subExpressions);
        } else {
            criterion = queryBuilder.ilike(key, valueStr, matchMode);
        }

        return criterion;
    }

    private static Criterion parseNumber(String key, String valueStr, String type, QueryBuilder queryBuilder) {
        String operator = "";
        Object value = null;
        Criterion criterion = null;

        System.out.println("parseNumber:: valueStr = " + valueStr);
        if (valueStr.trim().startsWith("[")) {
            operator = valueStr.trim().substring(1, valueStr.indexOf("]"));
            valueStr = valueStr.trim().substring(valueStr.indexOf("]") + 1);
        }

        switch (type) {
            case "(I)":
                value = Integer.parseInt(valueStr);
                break;
            case "(L)":
                value = Long.parseLong(valueStr);
                break;
            case "(d)":
                value = Double.parseDouble(valueStr);
                break;
            default:
                value = valueStr;
        }
        switch (operator) {
            case "ne":
                criterion = queryBuilder.ne(key, value);
                break;
            case "gt":
                criterion = queryBuilder.gt(key, value);
                break;
            case "lt":
                criterion = queryBuilder.lt(key, value);
                break;
            case "ge":
                criterion = queryBuilder.ge(key, value);
                break;
            case "le":
                criterion = queryBuilder.le(key, value);
                break;
            case "eq":
            default:
                criterion = queryBuilder.eq(key, value);
                break;

        }

        if (criterion == null) {
            System.out.println("criterion is NULL");
        }

        return criterion;
    }

    private static Criterion parseDate(String key, String value, QueryBuilder queryBuilder) {
        Criterion criterion = null;
        if (value.contains(",")) {
            System.out.println("value = " + value);
            String[] dates = value.split(",");

            System.out.println("date1 = " + dates[0]);
            System.out.println("date2 = " + dates[1]);
            Criterion startDateCriterion = null, endDateCriterion = null;
            if (hasValue(dates[0])) {
                startDateCriterion = queryBuilder.ge(key, getSQLDate(dates[0], 0));
            }

            if (hasValue(dates[1])) {
                endDateCriterion = queryBuilder.lt(key, getSQLDate(dates[1], 86399000));
            }

            if (startDateCriterion != null && endDateCriterion != null) {
                criterion = queryBuilder.and(startDateCriterion, endDateCriterion);
            } else {
                criterion = startDateCriterion == null ? endDateCriterion : startDateCriterion;
            }

        } else {
            criterion = queryBuilder.eq(key, new Date(value));
        }
        return criterion;
    }

    private static Date getSQLDate(String longStr, Integer extra) {
        Long millis = Long.parseLong(longStr) + extra;
        return new Date(millis);
    }

    private static boolean hasValue(String str) {
        return str != null && !str.isEmpty() && !str.equalsIgnoreCase("null");
    }

}
