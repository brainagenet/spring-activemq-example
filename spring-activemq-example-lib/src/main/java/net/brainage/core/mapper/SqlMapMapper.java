/*
 *
 */
package net.brainage.core.mapper;

import org.apache.ibatis.session.ResultHandler;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * Created by ms29.seo on 2016-08-26.
 */
public class SqlMapMapper extends SqlSessionDaoSupport {

    public void select(String sqlId, Object parameterObject, ResultHandler resultHandler) {
        getSqlSession().select(sqlId, parameterObject, resultHandler);
    }

    public void select(String sqlId, ResultHandler resultHandler) {
        getSqlSession().select(sqlId, resultHandler);
    }

    public void update(String sqlId, Object parameterObject) {
        getSqlSession().update(sqlId, parameterObject);
    }

}
