package havecontrol.com.br.havecontrol.service;

import java.util.List;

import br.com.havecontrol.entity.Cost;
import br.com.havecontrol.entity.EntityBase;


public interface CostService extends BaseService<Cost>{

	void deleteAll(List<? extends EntityBase> list);
}
