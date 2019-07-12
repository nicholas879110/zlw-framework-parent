package com.zlw.framework.facade;



import com.zlw.commons.core.ResultData;

import java.util.List;


/**
 * 通用Dubbo服务接口
 *
 * @author fukui
 */
public interface BaseFacade<T> {

    public ResultData<T> add(T entity);

    public ResultData<T> update(T entity);

    public ResultData<T> updateBySelective(T entity);

    public ResultData<T> delete(String... ids);

    public int queryByCount(T entity);

    public ResultData<List<T>> queryByList(T entity);

    public ResultData<T> queryById(Object id);

}
