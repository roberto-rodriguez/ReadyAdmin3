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
package com.system.dto.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Hash extends LinkedHashMap<String, Object> {

    public Hash() {
    }

    public Hash(Object... values) {
        if (values.length % 2 != 0) {
            throw new IllegalArgumentException("Expected pairs of key -> value");
        }
        for (int i = 0; i < values.length; i += 2) {
            put(values[i].toString(), values[i + 1]);
        }
    }

    public Hash(LinkedHashMap map) {
        this.putAll(map);
    } 

    public Hash getHash(String name) {
        Object obj = get(name);

        if (obj != null) {
            if (obj instanceof Hash) {
                return (Hash) obj;
            } else {
                if (obj instanceof LinkedHashMap) {
                    LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) get(name);
                    Hash hash = new Hash(map);
                    put(name, hash);
                    return hash;
                }
            }
        }
        return null;
    }

    public List<Hash> getHashList(String name) {
        List<LinkedHashMap<String, Object>> mapList = (List<LinkedHashMap<String, Object>>) get(name);

        List<Hash> result = new ArrayList<Hash>();
        if (mapList != null) {
            result = mapList.stream().map(m -> new Hash(m)).collect(Collectors.toList());
        }
        return result;
    }

    public Integer getId() {
        return getInt("id", 0);
    }

    public Integer getInteger(String name) {
        return getInt(name);
    }

    public Integer getInt(String name) {
        Object obj = get(name);
        if (obj == null || (obj instanceof String && ((String) obj).isEmpty())) {
            return null;
        }

        if (obj instanceof Integer) {
            return (Integer) obj;
        } else {
            try {
                Integer value = Integer.parseInt(obj.toString());
                put(name, value);
                return value;
            } catch (Exception e) {
                System.out.println("Error: Failed to convert " + obj + " to Integer");
                return null;
            }
        }
    }

    public Long getLong(String name) {
        Object obj = get(name);
        if (obj == null) {
            return null;
        }

        if (obj instanceof Long) {
            return (Long) obj;
        } else {
            try {
                Long value = Long.parseLong(obj.toString());
                put(name, value);
                return value;
            } catch (Exception e) {
                System.out.println("Error: Failed to convert " + obj + " to Long");
                return null;
            }
        }
    }

    public Date getDate(String name) {
        Object obj = get(name);
        if (obj == null) {
            return null;
        }

        try {
            if (obj instanceof Date) {
                return (Date) obj;
            } else {
                if (obj instanceof Long) {
                    Long value = (Long) obj;
                    Date date = new Date(value);
                    put(name, date);
                    return date;
                }
                System.out.println("Error: Failed to convert " + obj + " to Date");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Hash::getDate -> failed to convert " + obj + " to Date.");
            return null;
        }

    }

    public Double getDouble(String name) {
        Object obj = get(name);
        if (obj == null) {
            return null;
        }

        if (obj instanceof Double) {
            return (Double) obj;
        } else {
            try {
                Double value = Double.parseDouble(obj.toString());
                put(name, value);
                return value;
            } catch (Exception e) {
                System.out.println("Error: Failed to convert " + obj + " to Double..");
                return null;
            }
        }
    }

    public String getString(String prop) {
        Object obj = get(prop);

        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

     public static void main(String[] args) {
        Hash hash = new Hash(); 
        hash.put("bool", "");
 
        System.out.println(hash.getDouble("bool")); 
    }
    
    public Boolean getBoolean(String name) {
        Object obj = get(name);
        if (obj == null) {
            return null;
        }

        if (obj instanceof Boolean) {
            return (Boolean) obj;
        } else {
            try {
                Boolean value = Boolean.parseBoolean(obj.toString());
                put(name, value);
                return value;
            } catch (Exception e) {
                System.out.println("Error: Failed to convert " + obj + " to Boolean");
                return null;
            }
        }
    }

    public Integer getInt(String prop, Integer defaultValue) {
        if (containsKey(prop)) {
            Integer value = getInt(prop);

            if (value == null) {
                return defaultValue;
            } else {
                return value;
            }
        }
        return defaultValue;
    }

    public String getString(String prop, String defaultValue) {
        if (containsKey(prop)) {
            return getString(prop);
        }
        return defaultValue;
    }

    public Boolean getBoolean(String prop, Boolean defaultValue) {
        if (containsKey(prop)) {
            return getBoolean(prop);
        }
        return defaultValue;
    }

}
