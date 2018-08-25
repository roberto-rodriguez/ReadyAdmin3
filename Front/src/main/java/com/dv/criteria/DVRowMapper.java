/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dv.criteria;

import com.system.dto.FieldDTO;
import com.system.dto.request.Hash;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author roberto.rodriguez
 */
public class DVRowMapper implements RowMapper {

    private List<FieldDTO> fields;

    public DVRowMapper(List<FieldDTO> fields) {
        this.fields = fields;
    }

    @Override
    public Hash mapRow(ResultSet rs, int i) throws SQLException { 
        Hash hash = new Hash();
        for (FieldDTO field : fields) { 
             hash.put(field.getIdx(), extractValue(rs, field.getIdx(), field.getType()));
        }
        return hash;
    }

    private Object extractValue(ResultSet rs, String field, String type) throws SQLException {
        if (field == null || type == null) {
            return "";
        }

        switch (type) {
            case "String":
                return rs.getString(field);
            case "Double":
                return rs.getDouble(field);
            case "Integer":
                return rs.getInt(field);
            case "Boolean":
                return rs.getBoolean(field);
            case "Long":
                return rs.getLong(field);
            case "Date":
                Timestamp timestamp = rs.getTimestamp(field);
                if (timestamp != null) {
                    return timestamp.getTime();
                }
            default:
                return rs.getObject(field);
        }
    }
}
