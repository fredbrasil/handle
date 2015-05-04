package com.br.havecontrol.dao;

import android.content.ContentValues;

import com.br.havecontrol.entity.EntityBase;

import java.util.List;
import java.util.Map;

public interface BaseDao<T extends EntityBase> {

    void insert(T entity, ContentValues values);

    void delete(T entity);

    void update(T entity, ContentValues values);

    T findById(Long id);

    T findByIdAndColumn(String id, String column);

    List<T> findAll();

    List<T> findAllOrderBy(String orderBy);

    List<T> findAllByIdAndColumn(String id, String column);

    boolean existEntity(T entity, Map<String, String> mapColumnValue);

    List<T> findAllByColumnAndValue(Map<String, String> mapColumnValue);
}
