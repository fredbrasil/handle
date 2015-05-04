package com.br.havecontrol.service;

import com.br.havecontrol.entity.Category;
import com.br.havecontrol.entity.EntityBase;

import java.util.List;


public interface CategoryService extends BaseService<Category> {

    void deleteAll(List<? extends EntityBase> list);
}
