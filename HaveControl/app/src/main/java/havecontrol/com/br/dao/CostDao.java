package havecontrol.com.br.havecontrol.dao;

import android.database.Cursor;
import br.com.havecontrol.entity.Cost;

public interface CostDao extends BaseDao<Cost>{

	Cost cursorToObject(Cursor cursor);
}
