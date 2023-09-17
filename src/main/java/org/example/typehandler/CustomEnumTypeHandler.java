package org.example.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.example.enums.GcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(GcType.class)
public class CustomEnumTypeHandler extends BaseTypeHandler<org.example.enums.GcType> {
    private Class<GcType> type;

    public CustomEnumTypeHandler() {}

    public CustomEnumTypeHandler(Class<GcType> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, GcType parameter, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, parameter.getValue());
    }

    @Override
    public GcType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return GcType.fromValue(rs.getInt(columnName));
    }

    @Override
    public GcType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return GcType.fromValue(rs.getInt(columnIndex));
    }

    @Override
    public GcType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return GcType.fromValue(cs.getInt(columnIndex));
    }
}