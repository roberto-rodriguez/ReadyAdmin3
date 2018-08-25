/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.dao;

import com.system.dto.UserDTO;
import com.system.dto.request.Hash;
import com.system.model.User;
import com.system.util.DateUtil;
import java.util.UUID;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alejo
 */
@Repository
public class UserDAO extends AbstractBaseDAO<User, UserDTO> {

    @Override
    public void addOrder(Criteria criteria) {
        criteria.addOrder(Order.asc("firstName"));
    }

    @Override
    public void applyListProjection(Criteria criteria) {
        //criteria.add(Restrictions.eq("app.id", super.principal.getAppId()));
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("firstName").as("firstName"))
                .add(Projections.property("lastName").as("lastName"))
                .add(Projections.property("password").as("password"))
                .add(Projections.property("email").as("email"))
                .add(Projections.property("active").as("active"));

        criteria.setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(UserDTO.class));
    }

    public Boolean exist(String email) {
        return buildCriteria()
                .add(Restrictions.eq("email", email))
                .setMaxResults(1)
                .uniqueResult() != null;
    }

    public UserDTO login(Hash data) throws Exception {
        ProjectionList projectionList = Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("token").as("token"))
                .add(Projections.property("lastLogin").as("lastLogin"))
                .add(Projections.property("active").as("active"))
                .add(Projections.property("email").as("email"))
                .add(Projections.property("firstName").as("firstName"))
                .add(Projections.property("lastName").as("lastName"));

        Criteria criteria = buildCriteria()
                .setMaxResults(1)
                .setProjection(projectionList)
                .setResultTransformer(Transformers.aliasToBean(UserDTO.class));

        if (data.containsKey("token")) {
            criteria.add(Restrictions.eq("token", data.getString("token")));
        } else {
            criteria.add(Restrictions.eq("email", data.getString("email")))
                    .add(Restrictions.eq("password", data.getString("password")));
        }

        UserDTO user = (UserDTO) criteria.uniqueResult();

        if (user != null) { //Refresh token every time the user log in with username and Password
            if (!data.containsKey("token")) {
                String newToken = generateToken(user.getId());
                query("UPDATE _user SET token = '" + newToken + "', last_login = now()  WHERE id = " + user.getId());
                user.setToken(newToken);
            } else {
                query("UPDATE _user SET last_login = now() WHERE id = " + user.getId());
            }
        }

        return user;
    }

    public static void main(String[] args) {
        System.out.println(generateToken(1));
    }

    private static String generateToken(Integer userId) {
        return userId + "_" + DateUtil.getTodayDateFormat_YYMMddHHmmss() + "_" + UUID.randomUUID().toString();
    }

}
