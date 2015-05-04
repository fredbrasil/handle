package com.br.havecontrol.service.impl;

import android.content.ContentValues;
import android.content.Context;

import com.br.havecontrol.common.HaveControlManager;
import com.br.havecontrol.dao.impl.PlaceMapsDaoImpl;
import com.br.havecontrol.entity.EntityBase;
import com.br.havecontrol.entity.Place;
import com.br.havecontrol.entity.PlaceMaps;
import com.br.havecontrol.service.PlaceMapsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import havecontrol.com.br.havecontrol.R;

public class PlaceMapsServiceImpl extends BaseServiceImpl<PlaceMaps> implements PlaceMapsService {

    private PlaceMapsDaoImpl PlaceMapsDao;
    private Context context;

    public PlaceMapsServiceImpl(Context context) {
        super(PlaceMaps.class, context);
        this.context = context;
    }

    /**
     * :: Delete all PlaceMaps
     *
     * @param list <b>List with the entities that will be deleted</b>
     * @return
     */
    public void deleteAll(List<? extends EntityBase> list) {
        PlaceMapsDao = new PlaceMapsDaoImpl(context);

        for (EntityBase ret : list) {

            if (usedEntity((PlaceMaps) ret)) {
                PlaceMapsDao.delete((PlaceMaps) ret);
            } else {
                //TODO SHOW MESSAGE
            }
        }
    }

    /**
     * :: Find All by RevenueExtenseType
     *
     * @param value  Value of the column
     * @param column
     * @return All PlaceMaps that has the value of the column
     */
    public List<PlaceMaps> findAllByIdAndColumn(String value, String column) {
        PlaceMapsDao = new PlaceMapsDaoImpl(context);
        return PlaceMapsDao.findAllByIdAndColumn(value, column);
    }

    /**
     * :: Find all PlaceMaps of the database
     *
     * @param
     * @return All PlaceMaps
     */
    public List<PlaceMaps> findAll() {
        PlaceMapsDao = new PlaceMapsDaoImpl(context);
        return PlaceMapsDao.findAll();
    }

    /**
     * :: Find all PlaceMaps of the database sorted by a field
     *
     * @param orderBy Column name
     * @return All PlaceMaps sorted
     */
    public List<PlaceMaps> findAllOrderBy(String orderBy) {
        PlaceMapsDao = new PlaceMapsDaoImpl(context);
        return PlaceMapsDao.findAllOrderBy(orderBy);
    }

    /**
     * :: Find PlaceMaps by ID
     *
     * @param id
     * @return PlaceMaps
     */
    public PlaceMaps findById(Long id) {
        PlaceMapsDao = new PlaceMapsDaoImpl(context);
        return PlaceMapsDao.findById(id);
    }

    /**
     * :: Assemble map of the entity with respective values
     *
     * @param PlaceMaps <b>Entity PlaceMaps</b>
     * @return Map of fields with respectives values
     */
    protected ContentValues getMapValueField(PlaceMaps PlaceMaps) {

        ContentValues values = null;

        values = new ContentValues();
        values.put(PlaceMaps.COLUMN_DESCRIPTION, PlaceMaps.getDescription());
        values.put(Place.COLUMN_ID, PlaceMaps.getPlace().getId());
        values.put(PlaceMaps.COLUMN_LATITUDE, PlaceMaps.getLatitude());
        values.put(PlaceMaps.COLUMN_LONGITUDE, PlaceMaps.getLongitude());
        values.put(PlaceMaps.COLUMN_REGISTER_DATE, PlaceMaps.getRegisterDate());

        return values;
    }

    /**
     * :: Verify if the PlaceMaps is associated the other entity
     *
     * @param type
     * @return True if it used and false otherwise
     */
    protected Boolean usedEntity(PlaceMaps type) {
        //TODO modified
        return true;
    }

    /**
     * :: Validate the entity
     *
     * @param PlaceMaps <b>Entity that will be validated</b>
     * @return True if validated and false otherwise
     */
    protected Boolean validate(PlaceMaps PlaceMaps) {

        boolean valid = true;

        // verify if description is null or blank
        if (PlaceMaps.getDescription() == null || PlaceMaps.getDescription().trim().isEmpty()) {

            // add message erro
            HaveControlManager.getInstance().addMessage(R.string.place_map_description);
            valid = false;
        }

        // verify if latitude is null or blank
        if (PlaceMaps.getLatitude() == null) {

            // add message erro
            HaveControlManager.getInstance().addMessage(R.string.latitude);
            valid = false;
        }

        // verify if latitude is null or blank
        if (PlaceMaps.getLongitude() == null) {

            // add message erro
            HaveControlManager.getInstance().addMessage(R.string.longitude);
            valid = false;
        }

        // verify if place is null or blank
        if (PlaceMaps.getPlace() == null) {

            // add message erro
            HaveControlManager.getInstance().addMessage(R.string.place);
            valid = false;
        }

        // verify  if registerDate is null or blank
        if (PlaceMaps.getRegisterDate() == null || PlaceMaps.getRegisterDate().trim().isEmpty()) {

            // adiciona a mensagem de erro
            HaveControlManager.getInstance().addMessage(R.string.required_date);
            valid = false;
        }

        // verify if there is type in database
        if (existPlaceMaps(PlaceMaps)) {
            HaveControlManager.getInstance().addMessage(R.string.exist_type);
            valid = false;
        }

        return valid;

    }

    /**
     * :: Verify if exist entity in database
     *
     * @param PlaceMaps <b>Entity that will be verified</b>
     * @return True if exist and false otherwise
     */
    public boolean existPlaceMaps(PlaceMaps PlaceMaps) {

        PlaceMapsDao = new PlaceMapsDaoImpl(context);

        Map<String, String> mapColumnValue = new HashMap<String, String>();
        mapColumnValue.put("upper(" + PlaceMaps.COLUMN_DESCRIPTION + ")", PlaceMaps.getDescription().trim().toUpperCase());
        mapColumnValue.put(Place.COLUMN_ID, String.valueOf(PlaceMaps.getPlace().getId()));

        return PlaceMapsDao.existEntity(PlaceMaps, mapColumnValue);
    }
}
