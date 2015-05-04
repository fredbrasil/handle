package com.br.havecontrol.dao;

import android.database.Cursor;

import com.br.havecontrol.entity.Category;


public interface CategoryDao extends BaseDao<Category> {

    Category cursorToObject(Cursor cursor);
}
