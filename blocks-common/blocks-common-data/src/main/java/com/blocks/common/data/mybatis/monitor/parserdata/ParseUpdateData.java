package com.blocks.common.data.mybatis.monitor.parserdata;


import com.blocks.common.data.mybatis.monitor.ChangeData;
import com.blocks.common.data.mybatis.monitor.core.MybatisInvocation;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 修改数据解析
 *
 * @author luyanan
 * @since 2022/7/27
 **/
public class ParseUpdateData implements ParseData {
    @Override
    public List<ChangeData> parse(String commandName, MybatisInvocation mybatisInvocation) throws Throwable {

        MappedStatement mappedStatement = mybatisInvocation.getMappedStatement();
        Object updateParameterObject = mybatisInvocation.getParameter();
        BoundSql boundSql = mappedStatement.getBoundSql(mybatisInvocation.getParameter());
        String sql = boundSql.getSql();
        Statement statement = CCJSqlParserUtil.parse(sql);
        Update updateStatement = (Update) statement;
        Table table = updateStatement.getTable();

        Expression where = updateStatement.getWhere();



        return new ArrayList<>();
    }




}
