/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dv.criteria;

import java.util.Collection;
import java.util.Map;
import org.hibernate.criterion.MatchMode;
import org.hibernate.type.Type;
import java.util.List;
import java.util.stream.Stream;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Service;

/**
 *
 * @author roberto.rodriguez
 */
@Service
public class DVQueryBuilder implements QueryBuilder {

    public DVCriterion idEq(Object value) {
        return new DVCriterion("id", "=", value);
    }

    public DVCriterion eq(String propertyName, Object value) {
        return new DVCriterion(propertyName, "=", value);
    }

    public DVCriterion eqOrIsNull(String propertyName, Object value) {
        return new DVCriterion("OR", eq(propertyName, value), isNull(propertyName));
    }

    public DVCriterion ne(String propertyName, Object value) {
        return new DVCriterion(propertyName, "!=", value);
    }

    public DVCriterion neOrIsNotNull(String propertyName, Object value) {
        return new DVCriterion("OR", ne(propertyName, value), isNotNull(propertyName));
    }

    public DVCriterion like(String propertyName, Object value) {
        return new DVCriterion(propertyName, "like", "'" + value + "'");
    }

    public DVCriterion like(String propertyName, String value, MatchMode matchMode) {
        return new DVCriterion(propertyName, "like", applyMatchMode(value, matchMode));
    }

    public DVCriterion ilike(String propertyName, Object value) {
        return new DVCriterion(propertyName, "ilike", value);
    }

    public DVCriterion ilike(String propertyName, String value, MatchMode matchMode) {
        return new DVCriterion(propertyName, "ilike", applyMatchMode(value, matchMode));
    }

    public DVCriterion gt(String propertyName, Object value) {
        return new DVCriterion(propertyName, ">", value);
    }

    public DVCriterion lt(String propertyName, Object value) {
        return new DVCriterion(propertyName, "<", value);
    }

    public DVCriterion le(String propertyName, Object value) {
        return new DVCriterion(propertyName, "<=", value);
    }

    public DVCriterion ge(String propertyName, Object value) {
        return new DVCriterion(propertyName, ">=", value);
    }

    public DVCriterion between(String propertyName, Object lo, Object hi) {
        return new DVCriterion("AND", ge(propertyName, lo), le(propertyName, hi));
    }

    public DVCriterion in(String propertyName, Object[] values) {
        return new DVCriterion("OR", Stream.of(values).map(val -> new DVCriterion(propertyName, "=", val)).toArray(DVCriterion[]::new));
    }

    public DVCriterion in(String propertyName, Collection values) {
        return new DVCriterion("OR", Stream.of(values).map(val -> new DVCriterion(propertyName, "=", val)).toArray(DVCriterion[]::new));
    }

    public DVCriterion isNull(String propertyName) {
        return new DVCriterion(propertyName, "IS", "null");
    }

    public DVCriterion isNotNull(String propertyName) {
        return new DVCriterion(propertyName, "IS NOT", "null");
    }

    public DVCriterion eqProperty(String propertyName, String otherPropertyName) {
        return new DVCriterion(propertyName, "=", otherPropertyName, true);
    }

    public DVCriterion neProperty(String propertyName, String otherPropertyName) {
        return new DVCriterion(propertyName, "!=", otherPropertyName, true);
    }

    public DVCriterion ltProperty(String propertyName, String otherPropertyName) {
        return new DVCriterion(propertyName, "<", otherPropertyName, true);
    }

    public DVCriterion leProperty(String propertyName, String otherPropertyName) {
        return new DVCriterion(propertyName, "<=", otherPropertyName, true);
    }

    public DVCriterion gtProperty(String propertyName, String otherPropertyName) {
        return new DVCriterion(propertyName, ">", otherPropertyName, true);
    }

    public DVCriterion geProperty(String propertyName, String otherPropertyName) {
        return new DVCriterion(propertyName, "<=", otherPropertyName, true);
    }

    public DVCriterion and(Criterion lhs, Criterion rhs) {
        return new DVCriterion("AND", (DVCriterion) lhs, (DVCriterion) rhs);
    }

    public DVCriterion and(List<? extends Criterion> conditions) {
        return new DVCriterion("AND", conditions.stream().toArray(DVCriterion[]::new));
    }

    public DVCriterion or(Criterion lhs, Criterion rhs) {
        return new DVCriterion("OR", (DVCriterion) lhs, (DVCriterion) rhs);
    }

    public DVCriterion or(List<? extends Criterion> conditions) {
        return new DVCriterion("OR", conditions.stream().toArray(DVCriterion[]::new));
    }

    public DVCriterion not(Criterion expression) {
        return ((DVCriterion) expression).negate();
    }

    public DVCriterion sqlRestriction(String sql, Object[] values, Type[] types) {
        return null;//TODO
    }

    public DVCriterion sqlRestriction(String sql, Object value, Type type) {
        return null;//TODO
    }

    public DVCriterion sqlRestriction(String sql) {
        return null;//TODO
    }

    public DVCriterion conjunction(List<? extends Criterion> conditions) {
        return new DVCriterion("AND", conditions.stream().toArray(DVCriterion[]::new));
    }

    public DVCriterion disjunction(List<? extends Criterion> conditions) {
        return new DVCriterion("OR", conditions.stream().toArray(DVCriterion[]::new));
    }

    public DVCriterion allEq(Map<String, ?> propertyNameValues) {
        return new DVCriterion("AND", propertyNameValues.entrySet().stream().map(e -> new DVCriterion(e.getKey(), "=", e.getValue())).toArray(DVCriterion[]::new));
    }

    public DVCriterion isEmpty(String propertyName) {
        return new DVCriterion(propertyName, "=", "''");
    }

    public DVCriterion isNotEmpty(String propertyName) {
        return new DVCriterion(propertyName, "!=", "''");
    }

    private String applyMatchMode(String value, MatchMode matchMode) {
        String val = value;
        switch (matchMode) {
            case START:
                val = "'" + value + "%'";
                break;
            case END:
                val = "'%" + value + "'";
                break;
            case ANYWHERE:
                val = "'%" + value + "%'";
                break;
        }
        return val;
    }

}
