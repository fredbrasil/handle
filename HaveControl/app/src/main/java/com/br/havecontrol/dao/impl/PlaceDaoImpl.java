package com.br.havecontrol.dao.impl;

import android.content.Context;
import android.database.Cursor;

import com.br.havecontrol.dao.PlaceDao;
import com.br.havecontrol.entity.Category;
import com.br.havecontrol.entity.Place;

public class PlaceDaoImpl extends BaseDaoImpl<Place> implements PlaceDao {

    public PlaceDaoImpl(Context context) {
        super(Place.class, context);
    }

    /**
     * :: Assemble the entity Place
     *
     * @param cursor <b>Cursor with the data </b>
     * @return Entity Place filled
     */
    @Override
    public Place cursorToObject(Cursor cursor) {

        Place place = new Place();
        place.setId(cursor.getLong(cursor.getColumnIndex(Place.COLUMN_ID)));
        place.setDescription(cursor.getString(cursor.getColumnIndex(Place.COLUMN_DESCRIPTION)));

        Category type = new Category();
        type.setId(cursor.getLong(cursor.getColumnIndex(Category.COLUMN_ID)));

        place.setCategory(type);
        place.setRegisterDate(cursor.getString(cursor.getColumnIndex(Place.COLUMN_REGISTER_DATE)));

        return place;
    }
}
