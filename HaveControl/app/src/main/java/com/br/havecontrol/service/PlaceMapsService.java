package com.br.havecontrol.service;

import com.br.havecontrol.entity.EntityBase;
import com.br.havecontrol.entity.PlaceMaps;

import java.util.List;


public interface PlaceMapsService extends BaseService<PlaceMaps> {

    void deleteAll(List<? extends EntityBase> list);
}
