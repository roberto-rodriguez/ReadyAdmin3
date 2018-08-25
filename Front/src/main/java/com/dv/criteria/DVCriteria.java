/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dv.criteria;

import com.system.dto.FieldDTO;
import com.system.dto.request.Hash;
import com.system.dto.request.RequestDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author roberto.rodriguez
 */
enum STEP {
    PROJECTION, TARGET, RESTRICTIONS, ORDER, LIMIT, OFFSET
}

public class DVCriteria extends BaseCriteria {

    private static String JOIN = " INNER JOIN :table :table ON :entity.:field = :table.id";
    private String app;
    private String entity;
    private Integer offset;
    private Integer limit;
    private List<Order> orders;
    private List<DVCriterion> expressions = new ArrayList<>();
    private List<FieldDTO> fields;

    private JdbcTemplate jdbcTemplate;

    public DVCriteria(JdbcTemplate jdbcTemplate, RequestDTO request, List<FieldDTO> fields, String order) {
        this.app = request.getAppIdx();
        this.entity = request.getEntityIdx();
        this.fields = fields;
        this.jdbcTemplate = jdbcTemplate;
        this.expressions = request.getExpressions().stream().map(exp -> (DVCriterion) exp).collect(Collectors.toList());
        this.addOrder(order);
    }

    @Override
    public List list() throws HibernateException {
        Map<STEP, String> steps = new HashMap<>();
        steps.put(STEP.PROJECTION, projection());
        steps.put(STEP.TARGET, target());
        steps.put(STEP.RESTRICTIONS, restrictions());
        steps.put(STEP.ORDER, order());
        steps.put(STEP.LIMIT, limit(limit));
        steps.put(STEP.OFFSET, offset());

        System.out.println("DVCriteria -> list() :: " + fields.stream().map(FieldDTO::toString).collect(Collectors.joining(" ")));

        return jdbcTemplate.query(buildQuery(steps), new DVRowMapper(fields));
    }

    @Override
    public Object uniqueResult() throws HibernateException {
        Map<STEP, String> steps = new HashMap<>();
        steps.put(STEP.PROJECTION, projection());
        steps.put(STEP.TARGET, target());
        steps.put(STEP.RESTRICTIONS, restrictions());
        steps.put(STEP.LIMIT, limit(1));

        return jdbcTemplate.queryForObject(buildQuery(steps), new DVRowMapper(fields));
    }

    public Long count() throws HibernateException {
        Map<STEP, String> steps = new HashMap<>();
        steps.put(STEP.PROJECTION, " count(*) ");
        steps.put(STEP.TARGET, target());
        steps.put(STEP.RESTRICTIONS, restrictions());

        return jdbcTemplate.queryForObject(buildQuery(steps), Long.class);
    }

    private String buildQuery(Map<STEP, String> steps) {
        String query = "SELECT " + Stream.of(STEP.values())
                .filter(s -> steps.containsKey(s))
                .map(s -> steps.get(s))
                .collect(Collectors.joining(" "));

        System.out.println("query:: " + query);
        return query;
    }

    private String projection() {
        return fields.stream()
                .map(f -> ( f.getRef() == null ? "_" + entity + "." + f.getIdx() : f.getRef() ) + " AS " + f.getIdx())
                .collect(Collectors.joining(", "));
    }

    private String target() {
        return " FROM " + app + "." + entity + " _" + entity
                + fields.stream()
                        .filter(f -> f.getRef() != null)
                        .map(f -> new Hash("field", f.getIdx(), "table", f.getRef().split("\\.")[0]))
                        .map(hash -> JOIN
                        .replaceAll(":table", hash.getString("table"))
                        .replaceAll(":field", hash.getString("field"))
                        .replaceAll(":entity", "_" + entity))
                        .collect(Collectors.joining());
    }

    public String restrictions() {
        if (!expressions.isEmpty()) {
            return " WHERE " + (new DVCriterion(" AND ", expressions.stream().toArray(DVCriterion[]::new))).get();
        } else {
            return "";
        }
    }

    public String order() {
        if (orders != null && !orders.isEmpty()) {
            return " ORDER BY " + orders.stream().map(order -> order.toString()).collect(Collectors.joining(", "));
        } else {
            return "";
        }
    }

    public String limit(Integer limit) {
        return (limit != null && limit != 0) ? " LIMIT " + limit : "";
    }

    public String offset() {
        return (offset != null && offset != 0) ? " OFFSET " + offset : "";
    }

    @Override
    public Criteria add(Criterion criterion) {
        expressions.add((DVCriterion) criterion);
        return this;
    }

    @Override
    public Criteria addOrder(Order order) {
        orders.add(order);
        return this;
    }

    //field1:asc|field2:desc
    public void addOrder(String entityOrder) {
        if (entityOrder != null) {
            this.orders = Stream.of(entityOrder.split("\\|")).map(ord -> {
                String[] parts = ord.split(":");

                if (parts[1].equalsIgnoreCase("DESC")) {
                    return Order.desc(parts[0]);
                } else {
                    return Order.asc(parts[0]);
                }
            }).collect(Collectors.toList());
        }
    }

    @Override
    public Criteria setMaxResults(int i) {
        this.limit = i;
        return this;
    }

    @Override
    public Criteria setFirstResult(int i) {
        this.offset = i;
        return this;
    }

}
