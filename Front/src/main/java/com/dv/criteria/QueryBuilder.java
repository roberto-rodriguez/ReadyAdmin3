/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dv.criteria;

import java.util.Collection;
import java.util.List;
import java.util.Map; 
import org.hibernate.criterion.MatchMode; 
import org.hibernate.criterion.Criterion;
import org.hibernate.type.Type;

/**
 *
 * @author roberto.rodriguez
 */
public interface QueryBuilder {
    
    public Criterion idEq(Object value); 

    public Criterion eq(String propertyName, Object value);

    public Criterion eqOrIsNull(String propertyName, Object value);

    public Criterion ne(String propertyName, Object value);

    public Criterion neOrIsNotNull(String propertyName, Object value);

    public Criterion like(String propertyName, Object value);

    public Criterion like(String propertyName, String value, MatchMode matchMode);

    public Criterion ilike(String propertyName, Object value);

    public Criterion ilike(String propertyName, String value, MatchMode matchMode);

    public Criterion gt(String propertyName, Object value);
    
    public Criterion lt(String propertyName, Object value);

    public Criterion le(String propertyName, Object value);

    public Criterion ge(String propertyName, Object value);

    public Criterion between(String propertyName, Object lo, Object hi);

    public Criterion in(String propertyName, Object[] values);

    public Criterion in(String propertyName, Collection values);

    public Criterion isNull(String propertyName);

    public Criterion isNotNull(String propertyName);

    public Criterion eqProperty(String propertyName, String otherPropertyName);

    public Criterion neProperty(String propertyName, String otherPropertyName);

    public Criterion ltProperty(String propertyName, String otherPropertyName);

    public Criterion leProperty(String propertyName, String otherPropertyName);
    
    public Criterion gtProperty(String propertyName, String otherPropertyName);

    public Criterion geProperty(String propertyName, String otherPropertyName);

    public Criterion and(Criterion lhs, Criterion rhs);

    public Criterion and(List<? extends Criterion> conditions);

    public Criterion or(Criterion lhs, Criterion rhs);

    public Criterion or(List<? extends Criterion> conditions);

    public Criterion not(Criterion expression);

    public Criterion sqlRestriction(String sql, Object[] values, Type[] types);

    public Criterion sqlRestriction(String sql, Object value, Type type);

    public Criterion sqlRestriction(String sql);
  
    public Criterion conjunction(List<? extends Criterion> conditions);
 
    public Criterion disjunction(List<? extends Criterion> conditions);

    public Criterion allEq(Map<String, ?> propertyNameValues);

    public Criterion isEmpty(String propertyName);

    public Criterion isNotEmpty(String propertyName);


}
