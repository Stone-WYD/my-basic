package com.njxnet.yccc.config.constant.mybatis;


import com.njxnet.yccc.util.LongWrapperForDate;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(value = {LongWrapperForDate.class})
public class LongWrapperForDateTypeHandler extends BaseTypeHandler<LongWrapperForDate> {


    public LongWrapperForDateTypeHandler() {

    }

    public void setNonNullParameter(PreparedStatement ps, int i, LongWrapperForDate parameter, JdbcType jdbcType) throws SQLException {
        // 将 LongWrapperForDate 中 Long 类型的 dateTime 作为参数传入
        Long ordinal = parameter.getDateTime();
        ps.setLong(i, ordinal);
    }

    public LongWrapperForDate getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Long ordinal = rs.getLong(columnName);
        return ordinal == 0 && rs.wasNull() ? null : this.toLongWrapperForDate(ordinal);
    }

    public LongWrapperForDate getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Long ordinal = rs.getLong(columnIndex);
        return ordinal == 0 && rs.wasNull() ? null : this.toLongWrapperForDate(ordinal);
    }

    public LongWrapperForDate getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Long ordinal = cs.getLong(columnIndex);
        return ordinal == 0 && cs.wasNull() ? null : this.toLongWrapperForDate(ordinal);
    }

    private LongWrapperForDate toLongWrapperForDate(Long ordinal) {
        LongWrapperForDate longWrapperForDate = new LongWrapperForDate();
        longWrapperForDate.setDateTime(ordinal);
        return longWrapperForDate;
    }
}
