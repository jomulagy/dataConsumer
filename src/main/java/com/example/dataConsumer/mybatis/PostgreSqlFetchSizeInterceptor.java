package com.example.dataConsumer.mybatis;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class PostgreSqlFetchSizeInterceptor implements Interceptor {

    private static final ThreadLocal<Integer> FETCH_SIZE_HOLDER = new ThreadLocal<>();

    public static void setFetchSize(int fetchSize) {
        FETCH_SIZE_HOLDER.set(fetchSize);
    }

    public static void clear() {
        FETCH_SIZE_HOLDER.remove();
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Integer fetchSize = FETCH_SIZE_HOLDER.get();
        Object statement = invocation.proceed();
        if (statement instanceof Statement && fetchSize != null && fetchSize > 0) {
            StatementHandler handler = unwrap((StatementHandler) invocation.getTarget());
            handler.getParameterHandler().getParameterObject();
            ((Statement) statement).setFetchSize(fetchSize);
        }
        return statement;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
        // no-op
    }

    private StatementHandler unwrap(StatementHandler handler) {
        MetaObject metaObject = SystemMetaObject.forObject(handler);
        while (metaObject.hasGetter("delegate")) {
            Object delegate = metaObject.getValue("delegate");
            if (delegate instanceof StatementHandler) {
                handler = (StatementHandler) delegate;
                metaObject = SystemMetaObject.forObject(handler);
            } else {
                break;
            }
        }
        return handler;
    }
}
