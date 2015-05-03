package havecontrol.com.br.havecontrol.dao;

import android.database.Cursor;
import br.com.havecontrol.entity.RevenueExpenseType;

public interface RevenueExpenseTypeDao extends BaseDao<RevenueExpenseType>{

	RevenueExpenseType cursorToObject(Cursor cursor);
}
