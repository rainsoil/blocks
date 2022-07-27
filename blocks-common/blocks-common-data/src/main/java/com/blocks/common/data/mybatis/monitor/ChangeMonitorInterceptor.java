package com.blocks.common.data.mybatis.monitor;


import com.blocks.common.data.mybatis.monitor.AbstractMonitor;
import com.blocks.common.data.mybatis.monitor.core.MybatisInvocation;
import com.blocks.common.data.mybatis.monitor.parserdata.ParseContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 数据监控拦截器
 *
 * @author luyanan
 * @since 2022/7/27
 **/
@Intercepts(value = {@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class ChangeMonitorInterceptor implements Interceptor {

    /**
     * 监控类
     *
     * @since 2022/7/27
     */

    private AbstractMonitor monitor;
    /**
     * 数据库方言
     *
     * @since 2022/7/27
     */

    private AbstractDialect dbDialect;

    CopyOnWriteArrayList<String> whiteCopyList = new CopyOnWriteArrayList<>();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        Object result = null;
        if (target instanceof Executor) {
            final Object[] args = invocation.getArgs();
            MappedStatement ms = (MappedStatement) args[0];
            Object parameter =args[1];
            String commandName = ms.getSqlCommandType().name();

            MybatisInvocation mybatisInvocation = new MybatisInvocation(args, ms, parameter,
                    (Executor) target, dbDialect, 500, whiteCopyList);
            ParseContext parseContext = new ParseContext();
            List<ChangeData> changeTable = parseContext.parse(commandName, mybatisInvocation);
            if (changeTable != null) {
                monitor.listen(changeTable);
            }
            /**执行方法*/
            result = invocation.proceed();

        }
        return result;
    }


    public void setMonitor(AbstractMonitor monitor) {
        this.monitor = monitor;
    }
}
