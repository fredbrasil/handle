package com.br.havecontrol.dao;

import android.database.Cursor;

import com.br.havecontrol.entity.PlaceMaps;

public interface PlaceMapsDao extends BaseDao<PlaceMaps> {

    PlaceMaps cursorToObject(Cursor cursor);
}
