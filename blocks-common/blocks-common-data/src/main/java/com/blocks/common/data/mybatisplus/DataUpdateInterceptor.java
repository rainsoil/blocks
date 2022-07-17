package com.blocks.common.data.mybatisplus;

import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.parser.JsqlParserSupport;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;

import java.sql.Connection;

/**
 * 数据修改拦截器
 *
 * @author luyanan
 * @since 2022/7/17
 **/
@Slf4j
public class DataUpdateInterceptor extends JsqlParserSupport implements InnerInterceptor {

    @Override
    public void beforePrepare(StatementHandler sh, Connection connection, Integer transactionTimeout) {
        PluginUtils.MPStatementHandler handler = PluginUtils.mpStatementHandler(sh);
        MappedStatement ms = handler.mappedStatement();

        SqlCommandType sct = ms.getSqlCommandType();
        if (sct == SqlCommandType.UPDATE || sct == SqlCommandType.DELETE) {
            if (InterceptorIgnoreHelper.willIgnoreBlockAttack(ms.getId())) return;
            BoundSql boundSql = handler.boundSql();
            parserMulti(boundSql.getSql(), null);
        }
    }

    @Override
    protected void processDelete(Delete delete, int index, String sql, Object obj) {

        String name = delete.getTable().getName();
        Expression where = delete.getWhere();
        System.out.println(name + "---" + where.toString() + "---" + sql + "----" + obj);
    }


    @Override
    protected void processUpdate(Update update, int index, String sql, Object obj) {
        String name = update.getTable().getName();
        Expression where = update.getWhere();
        System.out.println(name + "---" + where.toString() + "---" + sql + "---" + obj);
    }

    @Override
    public void beforeGetBoundSql(StatementHandler sh) {
        PluginUtils.MPStatementHandler handler = PluginUtils.mpStatementHandler(sh);
        BoundSql boundSql = handler.boundSql();
    }
}
