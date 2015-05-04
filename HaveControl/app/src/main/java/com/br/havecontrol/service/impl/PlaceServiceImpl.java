package com.br.havecontrol.service.impl;

import android.content.ContentValues;
import android.content.Context;

import com.br.havecontrol.common.HaveControlManager;
import com.br.havecontrol.dao.impl.CostDaoImpl;
import com.br.havecontrol.dao.impl.PlaceDaoImpl;
import com.br.havecontrol.entity.Category;
import com.br.havecontrol.entity.Cost;
import com.br.havecontrol.entity.EntityBase;
import com.br.havecontrol.entity.Place;
import com.br.havecontrol.service.PlaceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import havecontrol.com.br.havecontrol.R;

public class PlaceServiceImpl extends BaseServiceImpl<Place> implements PlaceService {

    private PlaceDaoImpl placeDao;
    private Context context;

    public PlaceServiceImpl(Context context) {
        super(Place.class, context);
        this.context = context;
    }

    /**
     * :: Delete all Place
     *
     * @param list <b>List with the entities that will be deleted</b>
     * @return
     */
    public void deleteAll(List<? extends EntityBase> list) {
        placeDao = new PlaceDaoImpl(context);

        for (EntityBase ret : list) {

            if (usedEntity((Place) ret)) {
                placeDao.delete((Place) ret);
            } else {
                //TODO SHOW MESSAGE
            }
        }
    }

    /**
     * :: Find All by Place
     *
     * @param value  Value of the column
     * @param column
     * @return All Place that has the value of the column
     */
    public List<Place> findAllByIdAndColumn(String value, String column) {
        placeDao = new PlaceDaoImpl(context);
        return placeDao.findAllByIdAndColumn(value, column);
    }

    /**
     * :: Find all Place of the database
     *
     * @param
     * @return All Place
     */
    public List<Place> findAll() {
        placeDao = new PlaceDaoImpl(context);
        return placeDao.findAll();
    }

    /**
     * :: Find all Place of the database sorted by a field
     *
     * @param orderBy Column name
     * @return All Place sorted
     */
    public List<Place> findAllOrderBy(String orderBy) {
        placeDao = new PlaceDaoImpl(context);
        return placeDao.findAllOrderBy(orderBy);
    }

    /**
     * :: Find Place by ID
     *
     * @param id
     * @return Place
     */
    public Place findById(Long id) {
        placeDao = new PlaceDaoImpl(context);
        return placeDao.findById(id);
    }

    /**
     * :: Assemble map of the entity with respective values
     *
     * @param place <b>Entity Place</b>
     * @return Map of fields with respectives values
     */
    protected ContentValues getMapValueField(Place place) {

        ContentValues values = null;

        values = new ContentValues();
        values.put(Place.COLUMN_DESCRIPTION, place.getDescription());
        values.put(Category.COLUMN_ID, place.getCategory().getId());
        values.put(Place.COLUMN_REGISTER_DATE, place.getRegisterDate());

        return values;
    }

    /**
     * :: Verify if the Place is associated the other entity
     *
     * @param
     * @return True if it used and false otherwise
     */
    public Boolean usedEntity(Place place) {

        CostDaoImpl costDao = new CostDaoImpl(context);

        Map<String, String> mapColumnValue = new HashMap<String, String>();
        mapColumnValue.put(Place.COLUMN_ID, String.valueOf(place.getId()));

        return !costDao.existEntity(new Cost(), mapColumnValue);
    }

    /**
     * :: Validate the entity
     *
     * @param place <b>Entity that will be validated</b>
     * @return True if validated and false otherwise
     */
    protected Boolean validate(Place place) {

        boolean valid = true;

        // verify if description is null or blank
        if (place.getDescription() == null || place.getDescription().trim().isEmpty()) {

            // add message erro
            HaveControlManager.getInstance().addMessage(R.string.place_description);
            valid = false;
        }

        // verify if type is null or blank
        if (place.getCategory() == null) {

            // add message erro
            HaveControlManager.getInstance().addMessage(R.string.type_revenue_expense);
            valid = false;
        }

        // verify  if registerDate is null or blank
        if (place.getRegisterDate() == null || place.getRegisterDate().trim().isEmpty()) {

            // adiciona a mensagem de erro
            HaveControlManager.getInstance().addMessage(R.string.required_date);
            valid = false;
        }

        // verify if there is type in database
        if (existPlace(place)) {
            HaveControlManager.getInstance().addMessage(R.string.exist_type);
            valid = false;
        }

        return valid;

    }

    /**
     * :: Verify if exist entity in database
     *
     * @param place <b>Entity that will be verified</b>
     * @return True if exist and false otherwise
     */
    public boolean existPlace(Place place) {

        placeDao = new PlaceDaoImpl(context);

        Map<String, String> mapColumnValue = new HashMap<String, String>();
        mapColumnValue.put("upper(" + Place.COLUMN_DESCRIPTION + ")", place.getDescription().trim().toUpperCase());
        mapColumnValue.put(Category.COLUMN_ID, String.valueOf(place.getCategory().getId()));

        return placeDao.existEntity(place, mapColumnValue);
    }
}
