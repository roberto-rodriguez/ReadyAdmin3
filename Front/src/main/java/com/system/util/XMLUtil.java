/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.system.util;

import com.system.dto.request.Hash;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter; 
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author roberto.rodriguez
 */
public class XMLUtil {

    private static XStream magicApi = new XStream();

    static {
        magicApi.registerConverter(new MapEntryConverter());
        magicApi.alias("data", Map.class);
    }

    public static String hashToXML(Hash hash, String... fields) {
        Map map = new HashMap();

        for (String field : fields) {
            map.put(field, hash.getString(field));
        }

        return magicApi.toXML(map);
    }

    public static String toXML(Map map) {
        return magicApi.toXML(map);
    }

    public static Map fromXML(String xml) {
        return (Map) magicApi.fromXML(xml);
    }
}

class MapEntryConverter implements Converter {

    public boolean canConvert(Class clazz) {
        return AbstractMap.class.isAssignableFrom(clazz);
    }

    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {

        AbstractMap map = (AbstractMap) value;
        for (Object obj : map.entrySet()) {
            Map.Entry entry = (Map.Entry) obj;
            writer.startNode(entry.getKey().toString());
            Object val = entry.getValue();
            if (null != val) {
                writer.setValue(val.toString());
            }
            writer.endNode();
        }

    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {

        Map<String, String> map = new HashMap<String, String>();

        while (reader.hasMoreChildren()) {
            reader.moveDown();

            String key = reader.getNodeName(); // nodeName aka element's name
            String value = reader.getValue();
            map.put(key, value);

            reader.moveUp();
        }

        return map;
    }

}
