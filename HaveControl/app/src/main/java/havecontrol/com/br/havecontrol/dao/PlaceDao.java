package havecontrol.com.br.havecontrol.dao;

import android.database.Cursor;
import br.com.havecontrol.entity.Place;

public interface PlaceDao extends BaseDao<Place>{

	Place cursorToObject(Cursor cursor);
}
