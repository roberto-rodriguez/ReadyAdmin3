/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dv.criteria;

import java.util.Collection;
import java.util.Map;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.type.Type;
import org.hibernate.criterion.PropertyExpression;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author roberto.rodriguez
 */
@Service
public class HibernateQueryBuilder implements QueryBuilder {

    public Criterion idEq(Object value) {
        return Restrictions.idEq(value);
    }

    public SimpleExpression eq(String propertyName, Object value) {
        return Restrictions.eq(propertyName, value);
    }

    public Criterion eqOrIsNull(String propertyName, Object value) {
        return Restrictions.eqOrIsNull(propertyName, value);
    }

    public SimpleExpression ne(String propertyName, Object value) {
        return Restrictions.ne(propertyName, value);
    }

    public Criterion neOrIsNotNull(String propertyName, Object value) {
        return Restrictions.neOrIsNotNull(propertyName, value);
    }

    public SimpleExpression like(String propertyName, Object value) {
        return Restrictions.like(propertyName, value);
    }

    public SimpleExpression like(String propertyName, String value, MatchMode matchMode) {
        return Restrictions.like(propertyName, value);
    }

    public Criterion ilike(String propertyName, Object value) {
        return Restrictions.ilike(propertyName, value);
    }

    public Criterion ilike(String propertyName, String value, MatchMode matchMode) {
        return Restrictions.ilike(propertyName, value, matchMode);
    }

    public SimpleExpression gt(String propertyName, Object value) {
        return Restrictions.gt(propertyName, value);
    }

    public SimpleExpression lt(String propertyName, Object value) {
        return Restrictions.lt(propertyName, value);
    }

    public SimpleExpression le(String propertyName, Object value) {
        return Restrictions.le(propertyName, value);
    }

    public SimpleExpression ge(String propertyName, Object value) {
        return Restrictions.ge(propertyName, value);
    }

    public Criterion between(String propertyName, Object lo, Object hi) {
        return Restrictions.between(propertyName, lo, hi);
    }

    public Criterion in(String propertyName, Object[] values) {
        return Restrictions.in(propertyName, values);
    }

    public Criterion in(String propertyName, Collection values) {
        return Restrictions.in(propertyName, values);
    }

    public Criterion isNull(String propertyName) {
        return Restrictions.isNull(propertyName);
    }

    public Criterion isNotNull(String propertyName) {
        return Restrictions.isNotNull(propertyName);
    }

    public PropertyExpression eqProperty(String propertyName, String otherPropertyName) {
        return Restrictions.eqProperty(propertyName, otherPropertyName);
    }

    public PropertyExpression neProperty(String propertyName, String otherPropertyName) {
        return Restrictions.neProperty(propertyName, otherPropertyName);
    }

    public PropertyExpression ltProperty(String propertyName, String otherPropertyName) {
        return Restrictions.ltProperty(propertyName, otherPropertyName);
    }

    public PropertyExpression leProperty(String propertyName, String otherPropertyName) {
        return Restrictions.leProperty(propertyName, otherPropertyName);
    }

    public PropertyExpression gtProperty(String propertyName, String otherPropertyName) {
        return Restrictions.gtProperty(propertyName, otherPropertyName);
    }

    public PropertyExpression geProperty(String propertyName, String otherPropertyName) {
        return Restrictions.geProperty(propertyName, otherPropertyName);
    }

    public LogicalExpression and(Criterion lhs, Criterion rhs) {
        return Restrictions.and(lhs, rhs);
    }

    public Conjunction and(List<? extends Criterion> conditions) {
        return Restrictions.and(conditions.stream().toArray(Criterion[]::new));
    }

    public LogicalExpression or(Criterion lhs, Criterion rhs) {
        return Restrictions.or(lhs, rhs);
    }

    public Disjunction or(List<? extends Criterion> conditions) {
        return Restrictions.or();
    }

    public Criterion not(Criterion expression) {
        return Restrictions.not(expression);
    }

    public Criterion sqlRestriction(String sql, Object[] values, Type[] types) {
        return Restrictions.sqlRestriction(sql, values, types);
    }

    public Criterion sqlRestriction(String sql, Object value, Type type) {
        return Restrictions.sqlRestriction(sql, value, type);
    }

    public Criterion sqlRestriction(String sql) {
        return Restrictions.idEq(sql);
    }

    public Conjunction conjunction(List<? extends Criterion> conditions) {
        return Restrictions.conjunction(conditions.stream().toArray(Criterion[]::new));
    }

    public Disjunction disjunction(List<? extends Criterion> conditions) {
        return Restrictions.disjunction(conditions.stream().toArray(Criterion[]::new));
    }

    public Criterion allEq(Map<String, ?> propertyNameValues) {
        return Restrictions.idEq(propertyNameValues);
    }

    public Criterion isEmpty(String propertyName) {
        return Restrictions.idEq(propertyName);
    }

    public Criterion isNotEmpty(String propertyName) {
        return Restrictions.idEq(propertyName);
    } 
}
