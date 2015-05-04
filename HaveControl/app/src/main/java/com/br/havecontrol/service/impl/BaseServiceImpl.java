package com.br.havecontrol.service.impl;

import android.content.ContentValues;
import android.content.Context;

import com.br.havecontrol.dao.impl.BaseDaoImpl;
import com.br.havecontrol.entity.EntityBase;
import com.br.havecontrol.service.BaseService;

public abstract class BaseServiceImpl<T extends EntityBase> implements BaseService<T> {

    private BaseDaoImpl<T> dao;
    private Class<T> clazz;
    private Context context;

    public BaseServiceImpl(Class<T> clazz, Context context) {
        this.clazz = clazz;
        this.context = context;
    }

    /**
     * :: Insert the entity in database
     *
     * @param entity <b>New entity</b>
     * @return
     */
    @Override
    public void insert(T entity) {

        if (validate(entity)) {
            dao = new BaseDaoImpl<T>(clazz, context);
            dao.insert(entity, getMapValueField(entity));
        }
    }

    /**
     * :: Delete the entity
     *
     * @param entity <b>Entity will be removed</b>
     * @return
     */
    @Override
    public void delete(T entity) {

        if (usedEntity(entity)) {
            dao = new BaseDaoImpl<T>(clazz, context);
            dao.delete(entity);
        }
    }

    /**
     * :: Update the entity
     *
     * @param entity <b>Entity will be updated</b>
     * @return
     */
    @Override
    public void update(T entity) {

        if (validate(entity)) {
            dao = new BaseDaoImpl<T>(clazz, context);
            dao.update(entity, getMapValueField(entity));
        }
    }

    /**
     * :: Assemble map of the entity with respective values
     *
     * @param entity
     * @return Map of fields with respectives values
     */
    protected abstract ContentValues getMapValueField(T entity);

    /**
     * :: Validate the entity
     *
     * @param entity <b>Entity that will be validated</b>
     * @return True if validated and false otherwise
     */
    protected abstract Boolean validate(T entity);

    /**
     * :: Verify if the entity is associated the other entity
     *
     * @param entity
     * @return True if it used and false otherwise
     */
    protected abstract Boolean usedEntity(T entity);

}
