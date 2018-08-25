/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dv.criteria;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.engine.spi.TypedValue;

/**
 *
 * @author roberto.rodriguez
 */
public class DVCriterion implements Criterion {

    public static final DateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String op;
    private String propertyName;
    private String otherPropertyName;
    private Object value;
    private Boolean comparingProps; //Need to add this to make the Constructors different
    private boolean not;

    private List<DVCriterion> list = new ArrayList<>();

    public DVCriterion(String propertyName, String op, Object value) {
        this.op = op;
        this.propertyName = propertyName;
        this.comparingProps = false;
        setValue(value);
    }

    public DVCriterion(String propertyName, String op, String otherPropertyName, Boolean comparingProps) {
        this.op = op;
        this.propertyName = propertyName;
        this.otherPropertyName = otherPropertyName;
        this.comparingProps = comparingProps;
    }

    public DVCriterion(String op, DVCriterion... subCriterions) {
        this.op = op;
        list = Arrays.asList(subCriterions);
    }

    public String get() {
        String query = not ? " NOT " : "";
        if (list.isEmpty()) {
            query = " " + getPropertyName() + " " + getOp() + " " + String.valueOf(getValue());
        } else {
            if (list.size() == 1) {
                query = list.stream().map(q -> q.get()).collect(Collectors.joining());
            } else {
                query = list.stream().map(q -> q.get())
                        .collect(Collectors.joining(getOp(), " ( ", " ) "));
            }
        }
        return query;
    }

    public DVCriterion negate() {
        this.not = !this.not;
        return this;
    }

    @Override
    public String toSqlString(Criteria crtr, CriteriaQuery cq) throws HibernateException {
        return "";
    }

    @Override
    public TypedValue[] getTypedValues(Criteria crtr, CriteriaQuery cq) throws HibernateException {
        return null;
    }

    /**
     * @return the op
     */
    public String getOp() {
        return op;
    }

    /**
     * @param op the op to set
     */
    public void setOp(String op) {
        this.op = op;
    }

    /**
     * @return the propertyName
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * @return the otherPropertyName
     */
    public String getOtherPropertyName() {
        return otherPropertyName;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        if (value != null && value instanceof Date) {
            value = " '" + SQL_DATE_FORMAT.format(value) + "'::timestamp ";
        }

        this.value = value;
    }

    /**
     * @return the comparingProps
     */
    public Boolean getComparingProps() {
        return comparingProps;
    }

    /**
     * @param comparingProps the comparingProps to set
     */
    public void setComparingProps(Boolean comparingProps) {
        this.comparingProps = comparingProps;
    }

}
