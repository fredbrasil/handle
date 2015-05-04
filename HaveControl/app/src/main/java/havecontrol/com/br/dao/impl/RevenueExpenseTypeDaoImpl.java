package havecontrol.com.br.havecontrol.dao.impl;

import android.content.Context;
import android.database.Cursor;
import br.com.havecontrol.dao.RevenueExpenseTypeDao;
import br.com.havecontrol.entity.RevenueExpenseType;

public class RevenueExpenseTypeDaoImpl extends BaseDaoImpl<RevenueExpenseType> implements RevenueExpenseTypeDao{

	public RevenueExpenseTypeDaoImpl(Context context) {
		super(RevenueExpenseType.class,context);
	}	
	
	/**
	 *  :: Assemble the entity RevenueExpenseType
	 *  @param cursor <b>Cursor with the data </b>
	 *  @return Entity RevenueExpenseType filled
	 * */
	@Override
	public RevenueExpenseType cursorToObject(Cursor cursor) {
		
		RevenueExpenseType type = new RevenueExpenseType();
		type.setId(cursor.getLong(cursor.getColumnIndex(RevenueExpenseType.COLUMN_ID)));
		type.setDescription(cursor.getString(cursor.getColumnIndex(RevenueExpenseType.COLUMN_DESCRIPTION)));
		type.setType(cursor.getString(cursor.getColumnIndex(RevenueExpenseType.COLUMN_TYPE)));
		type.setRegisterDate(cursor.getString(cursor.getColumnIndex(RevenueExpenseType.COLUMN_REGISTER_DATE)));		
		
		return type;		
	}	
}
