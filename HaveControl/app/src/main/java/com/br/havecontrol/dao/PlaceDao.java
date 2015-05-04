package com.br.havecontrol.dao;

import android.database.Cursor;

import com.br.havecontrol.entity.Place;

public interface PlaceDao extends BaseDao<Place> {

    Place cursorToObject(Cursor cursor);
}
