package havecontrol.com.br.havecontrol.dao.impl;

import android.content.Context;
import android.database.Cursor;
import br.com.havecontrol.dao.PlaceMapsDao;
import br.com.havecontrol.entity.Place;
import br.com.havecontrol.entity.PlaceMaps;

public class PlaceMapsDaoImpl extends BaseDaoImpl<PlaceMaps> implements PlaceMapsDao{

	public PlaceMapsDaoImpl(Context context) {
		super(PlaceMaps.class,context);
	}	
	
	/**
	 *  :: Assemble the entity PlaceMaps
	 *  @param cursor <b>Cursor with the data </b>
	 *  @return Entity Place filled
	 * */
	@Override
	public PlaceMaps cursorToObject(Cursor cursor) {
		
		PlaceMaps placeMap = new PlaceMaps();
		placeMap.setId(cursor.getLong(cursor.getColumnIndex(PlaceMaps.COLUMN_ID)));
		placeMap.setDescription(cursor.getString(cursor.getColumnIndex(PlaceMaps.COLUMN_DESCRIPTION)));
		
		Place place = new Place();
		place.setId(cursor.getLong(cursor.getColumnIndex(Place.COLUMN_ID)));
		placeMap.setPlace(place);
		
		placeMap.setLatitude(cursor.getDouble(cursor.getColumnIndex(PlaceMaps.COLUMN_LATITUDE)));
		placeMap.setLongitude(cursor.getDouble(cursor.getColumnIndex(PlaceMaps.COLUMN_LONGITUDE)));
		
		placeMap.setRegisterDate(cursor.getString(cursor.getColumnIndex(PlaceMaps.COLUMN_REGISTER_DATE)));		
		
		return placeMap;		
	}	
}
