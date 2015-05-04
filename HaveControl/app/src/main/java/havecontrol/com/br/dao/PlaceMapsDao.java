package havecontrol.com.br.havecontrol.dao;

import android.database.Cursor;
import br.com.havecontrol.entity.PlaceMaps;

public interface PlaceMapsDao extends BaseDao<PlaceMaps>{

	PlaceMaps cursorToObject(Cursor cursor);
}
