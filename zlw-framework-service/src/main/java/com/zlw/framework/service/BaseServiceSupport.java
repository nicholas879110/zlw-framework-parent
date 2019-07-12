package com.zlw.framework.service;

import com.zlw.commons.core.ErrorCode;
import com.zlw.commons.core.ResultData;
import com.zlw.commons.exception.GlobalRuntimeException;
import com.zlw.commons.logging.exception.GlobalLoggerError;
import com.zlw.framework.dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * 通用业务处理基类抽象实现
 *
 * @param <T>
 * @author fukui
 */
public abstract class BaseServiceSupport<T> implements BaseService<T> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected abstract BaseDao<T> getDao();

    public ResultData<T> add(T entity) {
        try {
            getDao().add(entity);
        } catch (Exception e) {
            GlobalLoggerError.error(e);
            throw new GlobalRuntimeException(ErrorCode.ADD_FAILOR, ErrorCode.ADD_FAILOR_MSG);
        }
        return ResultData.newSuccessResultData();
    }

    public ResultData<T> update(T entity) {
        try {
            getDao().update(entity);
        } catch (Exception e) {
            GlobalLoggerError.error(e);
            throw new GlobalRuntimeException(ErrorCode.UPDATE_FAILOR, ErrorCode.UPDATE_FAILOR_MSG);
        }
        return ResultData.newSuccessResultData();
    }

    public ResultData<T> saveOrUpdate(T entity) {
        try {
            ResultData<T> resultData = this.add(entity);
            if (!ErrorCode.SUCCESS.equals(resultData.getCode())) {
                return this.update(entity);
            } else {
                return resultData;
            }
        } catch (Exception e) {
            logger.info("Insert entity failed then update entity!");
            return this.update(entity);
        }
    }

    public ResultData<T> updateBySelective(T entity) {
        try {
            getDao().updateBySelective(entity);
        } catch (Exception e) {
            GlobalLoggerError.error(e);
            throw new GlobalRuntimeException(ErrorCode.UPDATE_FAILOR, ErrorCode.UPDATE_FAILOR_MSG);
        }
        return ResultData.newSuccessResultData();
    }

    public ResultData<T> delete(Object... ids) {
        if (ids == null || ids.length < 1) {
            return ResultData.newResultData(ErrorCode.DEL_ID_FAILOR, ErrorCode.DEL_ID_FAILOR_MSG);
        }
        try {
            for (Object id : ids) {
                getDao().delete(id);
            }
        } catch (Exception e) {
            GlobalLoggerError.error(e);
            throw new GlobalRuntimeException(ErrorCode.DEL_FAILOR, ErrorCode.DEL_FAILOR_MSG);
        }
        return ResultData.newSuccessResultData();
    }

    public int queryByCount(T entity) {
        try {
            return getDao().queryByCount(entity);
        } catch (Exception e) {
            GlobalLoggerError.error(e);
            throw new GlobalRuntimeException(ErrorCode.QUERY_FAILOR, ErrorCode.QUERY_FAILOR_MSG);
        }
    }

    public ResultData<List<T>> queryByList(T entity) {
        try {
            return ResultData.newResultData(getDao().queryByList(entity));
        } catch (Exception e) {
            GlobalLoggerError.error(e);
            throw new GlobalRuntimeException(ErrorCode.QUERY_FAILOR, ErrorCode.QUERY_FAILOR_MSG);
        }
    }

    public ResultData<T> queryById(Object id) {
        try {
            return ResultData.newResultData(getDao().queryById(id));
        } catch (Exception e) {
            GlobalLoggerError.error(e);
            throw new GlobalRuntimeException(ErrorCode.QUERY_FAILOR, ErrorCode.QUERY_FAILOR_MSG);
        }

    }
}
