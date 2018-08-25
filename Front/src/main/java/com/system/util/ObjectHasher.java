/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.util;

import com.system.dto.request.Hash;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Arrays; 
import java.util.Objects;

/**
 *
 * @author roberto.rodriguez
 */
public class ObjectHasher {

    public static Hash toHash(Object bean) {
         Hash hash = new Hash();
        try { 
            Arrays.asList(Introspector.getBeanInfo(bean.getClass(), Object.class)
                    .getPropertyDescriptors())
                    .stream()
                    // filter out properties with setters only
                    .filter(pd -> Objects.nonNull(pd.getReadMethod()))
                    .forEach(pd -> { // invoke method to get value
                        try {
                            Object value = pd.getReadMethod().invoke(bean);
                            if (value != null) {
                                hash.put(pd.getName(), value);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }); 
        } catch (IntrospectionException e) {
             e.printStackTrace();
        }
        
        return hash;
    }
}
