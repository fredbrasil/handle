package com.br.havecontrol.dao.impl;

import android.content.Context;
import android.database.Cursor;

import com.br.havecontrol.common.enums.CostType;
import com.br.havecontrol.common.enums.EnumPaymentType;
import com.br.havecontrol.dao.CostDao;
import com.br.havecontrol.entity.Category;
import com.br.havecontrol.entity.Cost;
import com.br.havecontrol.entity.PaymentType;
import com.br.havecontrol.entity.Place;
import com.br.havecontrol.entity.PlaceMaps;

public class CostDaoImpl extends BaseDaoImpl<Cost> implements CostDao {

    public CostDaoImpl(Context context) {
        super(Cost.class, context);
    }

    /**
     * :: Assemble the entity Cost
     *
     * @param cursor <b>Cursor with the data </b>
     * @return Entity Cost filled
     */
    @Override
    public Cost cursorToObject(Cursor cursor) {

        Cost cost = new Cost();
        cost.setId(cursor.getLong(cursor.getColumnIndex(Cost.COLUMN_ID)));

        Cost costFather = new Cost();
        costFather.setId(cursor.getLong(cursor.getColumnIndex(Cost.COLUMN_ID_FATHER)));
        cost.setCostFather(costFather);

        String costType = cursor.getString(cursor.getColumnIndex(Cost.COLUMN_COST_TYPE));
        cost.setCostType(CostType.getCostType(costType));

        cost.setDateEnd(cursor.getString(cursor.getColumnIndex(Cost.COLUMN_DATE_END)));
        cost.setDateExpiration(cursor.getString(cursor.getColumnIndex(Cost.COLUMN_DATE_EXPIRATION)));
        cost.setDateStart(cursor.getString(cursor.getColumnIndex(Cost.COLUMN_DATE_START)));
        cost.setObservation(cursor.getString(cursor.getColumnIndex(Cost.COLUMN_OBSERVATION)));
        cost.setPaid(cursor.getString(cursor.getColumnIndex(Cost.COLUMN_PAID)));

        PaymentType pt = new PaymentType();
        pt.setAmount(cursor.getInt(cursor.getColumnIndex(Cost.COLUMN_AMOUNT)));
        pt.setDateStartPayment(cursor.getString(cursor.getColumnIndex(Cost.COLUMN_DATE_START_PAYMENT)));
        String paymentType = cursor.getString(cursor.getColumnIndex(Cost.COLUMN_PAYMENT_TYPE));
        pt.setPaymentType(EnumPaymentType.getPaymentType(paymentType));
        cost.setPaymentType(pt);

        Place place = new Place();
        place.setId(cursor.getLong(cursor.getColumnIndex(Place.COLUMN_ID)));
        cost.setPlace(place);

        PlaceMaps placeMap = new PlaceMaps();
        placeMap.setId(cursor.getLong(cursor.getColumnIndex(PlaceMaps.COLUMN_ID)));
        cost.setPlaceMap(placeMap);

        cost.setPrice(cursor.getDouble(cursor.getColumnIndex(Cost.COLUMN_PRICE)));

        Category type = new Category();
        type.setId(cursor.getLong(cursor.getColumnIndex(Category.COLUMN_ID)));

        cost.setCategory(type);
        cost.setRegisterDate(cursor.getString(cursor.getColumnIndex(Cost.COLUMN_REGISTER_DATE)));

        return cost;
    }
}
