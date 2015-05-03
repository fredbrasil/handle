package havecontrol.com.br.havecontrol.service;

import java.util.List;

import br.com.havecontrol.entity.EntityBase;
import br.com.havecontrol.entity.Place;


public interface PlaceService extends BaseService<Place>{

	void deleteAll(List<? extends EntityBase> list);
}
