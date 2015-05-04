package com.br.havecontrol.dao;

import android.database.Cursor;

import com.br.havecontrol.entity.Cost;

public interface CostDao extends BaseDao<Cost> {

    Cost cursorToObject(Cursor cursor);
}
