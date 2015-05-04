package com.br.havecontrol.service.impl;

import android.content.ContentValues;
import android.content.Context;

import com.br.havecontrol.common.HaveControlManager;
import com.br.havecontrol.dao.impl.CategoryDaoImpl;
import com.br.havecontrol.dao.impl.PlaceDaoImpl;
import com.br.havecontrol.entity.Category;
import com.br.havecontrol.entity.EntityBase;
import com.br.havecontrol.entity.Place;
import com.br.havecontrol.service.CategoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import havecontrol.com.br.havecontrol.R;

public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {

    private CategoryDaoImpl typeDao;
    private Context context;

    public CategoryServiceImpl(Context context) {
        super(Category.class, context);
        this.context = context;
    }

    /**
     * :: Delete all Category
     *
     * @param list <b>List with the entities that will be deleted</b>
     * @return
     */
    public void deleteAll(List<? extends EntityBase> list) {
        typeDao = new CategoryDaoImpl(context);

        boolean titlemsg = false;

        for (EntityBase ret : list) {

            if (usedEntity((Category) ret)) {
                typeDao.delete((Category) ret);
            } else {
                if (!titlemsg) {
                    HaveControlManager.getInstance().addMessage(R.string.error_remove_category);
                    titlemsg = true;
                }
                HaveControlManager.getInstance().addMessage(ret.getDescription());
            }
        }

        HaveControlManager.getInstance().showToastMessage();
    }

    /**
     * :: Find all Category of the database
     *
     * @param
     * @return All Category
     */
    public List<Category> findAll() {
        typeDao = new CategoryDaoImpl(context);
        return typeDao.findAll();
    }

    /**
     * :: Find all Category of the database sorted by a field
     *
     * @param orderBy Column name
     * @return All Category sorted
     */
    public List<Category> findAllOrderBy(String orderBy) {
        typeDao = new CategoryDaoImpl(context);
        return typeDao.findAllOrderBy(orderBy);
    }

    /**
     * :: Find All by Category
     *
     * @param value  Value of the column
     * @param column
     * @return All Category that has the value of the column
     */
    public List<Category> findAllByIdAndColumn(String value, String column) {
        typeDao = new CategoryDaoImpl(context);
        return typeDao.findAllByIdAndColumn(value, column);
    }

    /**
     * :: Find Category by ID
     *
     * @param id
     * @return Category
     */
    public Category findById(Long id) {
        typeDao = new CategoryDaoImpl(context);
        return typeDao.findById(id);
    }

    /**
     * :: Assemble map of the entity with respective values
     *
     * @param type <b>Entity Category</b>
     * @return Map of fields with respectives values
     */
    protected ContentValues getMapValueField(Category type) {

        ContentValues values = null;

        values = new ContentValues();
        values.put(Category.COLUMN_DESCRIPTION, type.getDescription());
        values.put(Category.COLUMN_TYPE, type.getType());
        values.put(Category.COLUMN_REGISTER_DATE, type.getRegisterDate());

        return values;
    }

    /**
     * :: Verify if the Category is associated the other entity
     *
     * @param type
     * @return True if it used and false otherwise
     */
    protected Boolean usedEntity(Category type) {

        PlaceDaoImpl placeDao = new PlaceDaoImpl(context);

        Map<String, String> mapColumnValue = new HashMap<String, String>();
        mapColumnValue.put(Category.COLUMN_ID, String.valueOf(type.getId()));

        return !placeDao.existEntity(new Place(), mapColumnValue);

    }

    /**
     * :: Validate the entity
     *
     * @param type <b>Entity that will be validated</b>
     * @return True if validated and false otherwise
     */
    protected Boolean validate(Category type) {

        boolean valid = true;

        // verify if description is null or blank
        if (type.getDescription() == null || type.getDescription().trim().isEmpty()) {

            // add message erro
            HaveControlManager.getInstance().addMessage(R.string.type_description);
            valid = false;
        }

        // verify if type is null or blank
        if (type.getType() == null || type.getType().trim().isEmpty()) {

            // add message erro
            HaveControlManager.getInstance().addMessage(R.string.type_revenue_expense);
            valid = false;
        }

        // verify  if registerDate is null or blank
        if (type.getRegisterDate() == null || type.getRegisterDate().trim().isEmpty()) {

            // adiciona a mensagem de erro
            HaveControlManager.getInstance().addMessage(R.string.required_date);
            valid = false;
        }

        // verify if there is type in database
        if (existCategory(type)) {
            HaveControlManager.getInstance().addMessage(R.string.exist_type);
            valid = false;
        }

        return valid;

    }

    /**
     * :: Verify if exist entity in database
     *
     * @param type <b>Entity that will be verified</b>
     * @return True if exist and false otherwise
     */
    public boolean existCategory(Category type) {

        typeDao = new CategoryDaoImpl(context);

        Map<String, String> mapColumnValue = new HashMap<String, String>();
        mapColumnValue.put("upper(" + Category.COLUMN_DESCRIPTION + ")", type.getDescription().trim().toUpperCase());
        mapColumnValue.put(Category.COLUMN_TYPE, type.getType());

        return typeDao.existEntity(type, mapColumnValue);
    }
}
