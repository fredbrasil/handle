package havecontrol.com.br.havecontrol.service.impl;

import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import br.com.havecontrol.R;
import br.com.havecontrol.common.HaveControlManager;
import br.com.havecontrol.dao.impl.CostDaoImpl;
import br.com.havecontrol.entity.Cost;
import br.com.havecontrol.entity.EntityBase;
import br.com.havecontrol.entity.Place;
import br.com.havecontrol.entity.PlaceMaps;
import br.com.havecontrol.entity.RevenueExpenseType;
import br.com.havecontrol.service.CostService;

public class CostServiceImpl extends BaseServiceImpl<Cost> implements CostService{

	private CostDaoImpl costDao;
	private Context context;
	
	public CostServiceImpl(Context context) {
		super(Cost.class, context);
		this.context = context;
	}	

	/**
	 *  :: Delete all Cost
	 *  @param list <b>List with the entities that will be deleted</b>
	 *  @return 
	 * */
	public void deleteAll(List<? extends EntityBase> list){
		costDao = new CostDaoImpl(context);
		
		for(EntityBase ret : list){
			
			if(usedEntity((Cost)ret)){
				costDao.delete((Cost)ret);
			}else{
				//TODO SHOW MESSAGE
			}
		}	    
	}
	
	/**
	 *  :: Find All by Cost
	 *  @param value Value of the column
	 *  @param column 
	 *  @return All Cost that has the value of the column
	 * */
	public List<Cost> findAllByIdAndColumn(String value,String column) {
		costDao = new CostDaoImpl(context);
	    return costDao.findAllByIdAndColumn(value, column);
	}

	/**
	 *  :: Find All by Cost
	 *  @param value Value of the column
	 *  @param column 
	 *  @return All Cost that has the value of the column
	 * */
	public List<Cost> findAllByColumnAndValue(Map<String,String> mapColumnValue) {
		costDao = new CostDaoImpl(context);
	    return costDao.findAllByColumnAndValue(mapColumnValue);
	}

	
	/**
	 *  :: Find all Cost of the database
	 *  @param 
	 *  @return All Cost
	 * */
	public List<Cost> findAll() {
		costDao = new CostDaoImpl(context);
	    return costDao.findAll();
	}

	/**
	 *  :: Find all Cost of the database sorted by a field
	 *  @param orderBy Column name
	 *  @return All Cost sorted
	 * */
	public List<Cost> findAllOrderBy(String orderBy) {
		costDao = new CostDaoImpl(context);
	    return costDao.findAllOrderBy(orderBy);
	}
	
	/**
	 *  :: Find Cost by ID
	 *  @param id
	 *  @return Cost
	 * */
	public Cost findById(Long id) {
		costDao = new CostDaoImpl(context);
	    return costDao.findById(id);
	}
	
	/**
	 *  :: Assemble map of the entity with respective values
	 *  @param cost <b>Entity Cost</b>
	 *  @return Map of fields with respectives values
	 * */
	protected ContentValues getMapValueField(Cost cost){
		
		ContentValues values = null;
		
		values = new ContentValues();
	    values.put(Cost.COLUMN_AMOUNT, cost.getPaymentType().getAmount());
	    values.put(Cost.COLUMN_COST_TYPE, cost.getCostType().getCode());
	    values.put(Cost.COLUMN_DATE_END, cost.getDateEnd());
	    values.put(Cost.COLUMN_DATE_EXPIRATION, cost.getDateExpiration());
	    values.put(Cost.COLUMN_DATE_START, cost.getDateStart());
	    values.put(Cost.COLUMN_DATE_START_PAYMENT, cost.getPaymentType().getDateStartPayment());
	    values.put(Cost.COLUMN_ID, cost.getId());
	    values.put(Cost.COLUMN_OBSERVATION, cost.getObservation());
	    values.put(Cost.COLUMN_PAID, cost.getPaid());
	    values.put(Cost.COLUMN_PAYMENT_TYPE, cost.getPaymentType().getPaymentType().getCode());
	    values.put(Cost.COLUMN_PRICE, cost.getPrice());
	    values.put(Place.COLUMN_ID, cost.getPlace().getId());
	    values.put(RevenueExpenseType.COLUMN_ID, cost.getRevenueExpenseType().getId());
	    values.put(Cost.COLUMN_REGISTER_DATE, cost.getRegisterDate());	    
	    
	    if(cost.getCostFather() != null){
	    	values.put(Cost.COLUMN_ID_FATHER, cost.getCostFather().getId());
	    }
	    
	    if(cost.getPlaceMap() != null){
	    	values.put(PlaceMaps.COLUMN_ID, cost.getPlaceMap().getId());
	    }
	    
	    
	    return values;
	}
	
	/**
	 *  :: Verify if the Cost is associated the other entity
	 *  @param type
	 *  @return True if it used and false otherwise
	 * */
	protected Boolean usedEntity(Cost type){
		//TODO modified
		return true;
	}
	
	/**
	 *  :: Validate the entity
	 *  @param cost <b>Entity that will be validated</b>
	 *  @return True if validated and false otherwise
	 * */
	protected Boolean validate(Cost cost){
		
		boolean valid = true;
		
		// verify if cost type is null
		if(cost.getCostType() == null){
			
			// add message erro
			HaveControlManager.getInstance().addMessage(R.string.error_costtype);
			valid = false;
		}		
		
		// verify if type is null or blank
		if(cost.getRevenueExpenseType() == null){
			
			// add message erro
			HaveControlManager.getInstance().addMessage(R.string.type_revenue_expense);
			valid = false;
		}
		
		// verify if place is null or blank
		if(cost.getPlace() == null){
			
			// add message erro
			HaveControlManager.getInstance().addMessage(R.string.error_place);
			valid = false;
		}
		
		// verify if payment type is null or blank
		if(cost.getPaymentType() == null){
			
			// add message erro
			HaveControlManager.getInstance().addMessage(R.string.error_paymentType);
			valid = false;
		}
		
		// verify if price is null or blank
		if(cost.getPrice() == null){
			
			// add message erro
			HaveControlManager.getInstance().addMessage(R.string.error_price);
			valid = false;
		}
		
		// verify  if registerDate is null or blank
		if(cost.getRegisterDate() == null || cost.getRegisterDate().trim().isEmpty()){
			
			// adiciona a mensagem de erro
			HaveControlManager.getInstance().addMessage(R.string.required_date);
			valid = false;
		}
			
		return valid;
		
	}
	
}
