package havecontrol.com.br.havecontrol.service;

import java.util.List;

import br.com.havecontrol.entity.EntityBase;
import br.com.havecontrol.entity.RevenueExpenseType;


public interface RevenueExpenseTypeService extends BaseService<RevenueExpenseType>{

	void deleteAll(List<? extends EntityBase> list);
}
