package havecontrol.com.br.havecontrol.dao.impl;

import android.content.Context;
import android.database.Cursor;
import br.com.havecontrol.dao.PlaceDao;
import br.com.havecontrol.entity.Place;
import br.com.havecontrol.entity.RevenueExpenseType;

public class PlaceDaoImpl extends BaseDaoImpl<Place> implements PlaceDao{

	public PlaceDaoImpl(Context context) {
		super(Place.class,context);
	}	
	
	/**
	 *  :: Assemble the entity Place
	 *  @param cursor <b>Cursor with the data </b>
	 *  @return Entity Place filled
	 * */
	@Override
	public Place cursorToObject(Cursor cursor) {
		
		Place place = new Place();
		place.setId(cursor.getLong(cursor.getColumnIndex(Place.COLUMN_ID)));
		place.setDescription(cursor.getString(cursor.getColumnIndex(Place.COLUMN_DESCRIPTION)));
		
		RevenueExpenseType type = new RevenueExpenseType();
		type.setId(cursor.getLong(cursor.getColumnIndex(RevenueExpenseType.COLUMN_ID)));
		
		place.setRevenueExpenseType(type);
		place.setRegisterDate(cursor.getString(cursor.getColumnIndex(Place.COLUMN_REGISTER_DATE)));		
		
		return place;		
	}	
}
