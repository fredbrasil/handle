package com.br.havecontrol.dao.impl;

import android.content.Context;
import android.database.Cursor;

import com.br.havecontrol.dao.CategoryDao;
import com.br.havecontrol.entity.Category;

public class CategoryDaoImpl extends BaseDaoImpl<Category> implements CategoryDao {

    public CategoryDaoImpl(Context context) {
        super(Category.class, context);
    }

    /**
     * :: Assemble the entity Category
     *
     * @param cursor <b>Cursor with the data </b>
     * @return Entity Category filled
     */
    @Override
    public Category cursorToObject(Cursor cursor) {

        Category type = new Category();
        type.setId(cursor.getLong(cursor.getColumnIndex(Category.COLUMN_ID)));
        type.setDescription(cursor.getString(cursor.getColumnIndex(Category.COLUMN_DESCRIPTION)));
        type.setType(cursor.getString(cursor.getColumnIndex(Category.COLUMN_TYPE)));
        type.setRegisterDate(cursor.getString(cursor.getColumnIndex(Category.COLUMN_REGISTER_DATE)));

        return type;
    }
}
