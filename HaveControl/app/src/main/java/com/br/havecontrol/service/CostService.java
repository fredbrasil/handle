package com.br.havecontrol.service;

import com.br.havecontrol.entity.Cost;
import com.br.havecontrol.entity.EntityBase;

import java.util.List;


public interface CostService extends BaseService<Cost> {

    void deleteAll(List<? extends EntityBase> list);
}
