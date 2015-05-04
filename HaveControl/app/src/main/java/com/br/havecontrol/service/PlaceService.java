package com.br.havecontrol.service;

import com.br.havecontrol.entity.EntityBase;
import com.br.havecontrol.entity.Place;

import java.util.List;


public interface PlaceService extends BaseService<Place> {

    void deleteAll(List<? extends EntityBase> list);
}
