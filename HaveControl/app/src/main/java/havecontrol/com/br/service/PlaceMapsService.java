package havecontrol.com.br.havecontrol.service;

import java.util.List;

import br.com.havecontrol.entity.EntityBase;
import br.com.havecontrol.entity.PlaceMaps;


public interface PlaceMapsService extends BaseService<PlaceMaps>{

	void deleteAll(List<? extends EntityBase> list);
}
