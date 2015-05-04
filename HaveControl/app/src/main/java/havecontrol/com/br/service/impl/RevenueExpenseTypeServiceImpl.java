package havecontrol.com.br.havecontrol.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import br.com.havecontrol.R;
import br.com.havecontrol.common.HaveControlManager;
import br.com.havecontrol.dao.impl.PlaceDaoImpl;
import br.com.havecontrol.dao.impl.RevenueExpenseTypeDaoImpl;
import br.com.havecontrol.entity.EntityBase;
import br.com.havecontrol.entity.Place;
import br.com.havecontrol.entity.RevenueExpenseType;
import br.com.havecontrol.service.RevenueExpenseTypeService;

public class RevenueExpenseTypeServiceImpl extends BaseServiceImpl<RevenueExpenseType> implements RevenueExpenseTypeService{

	private RevenueExpenseTypeDaoImpl typeDao;
	private Context context;
	
	public RevenueExpenseTypeServiceImpl(Context context) {
		super(RevenueExpenseType.class, context);
		this.context = context;
	}	

	/**
	 *  :: Delete all RevenueExpenseType
	 *  @param list <b>List with the entities that will be deleted</b>
	 *  @return 
	 * */
	public void deleteAll(List<? extends EntityBase> list){
		typeDao = new RevenueExpenseTypeDaoImpl(context);
		
		boolean titlemsg = false;
		
		for(EntityBase ret : list){
			
			if(usedEntity((RevenueExpenseType)ret)){
				typeDao.delete((RevenueExpenseType)ret);
			}else{
				if(!titlemsg){
					HaveControlManager.getInstance().addMessage(R.string.error_remove_category);
					titlemsg = true;
				}
				HaveControlManager.getInstance().addMessage(ret.getDescription());
			}
		}
		
		HaveControlManager.getInstance().showToastMessage();
	}
	
	/**
	 *  :: Find all RevenueExpenseType of the database
	 *  @param 
	 *  @return All RevenueExpenseType
	 * */
	public List<RevenueExpenseType> findAll() {
		typeDao = new RevenueExpenseTypeDaoImpl(context);
	    return typeDao.findAll();
	}

	/**
	 *  :: Find all RevenueExpenseType of the database sorted by a field
	 *  @param orderBy Column name
	 *  @return All RevenueExpenseType sorted
	 * */
	public List<RevenueExpenseType> findAllOrderBy(String orderBy) {
		typeDao = new RevenueExpenseTypeDaoImpl(context);
	    return typeDao.findAllOrderBy(orderBy);
	}
	
	/**
	 *  :: Find All by RevenueExpenseType
	 *  @param value Value of the column
	 *  @param column 
	 *  @return All RevenueExpenseType that has the value of the column
	 * */
	public List<RevenueExpenseType> findAllByIdAndColumn(String value,String column) {
		typeDao = new RevenueExpenseTypeDaoImpl(context);
	    return typeDao.findAllByIdAndColumn(value, column);
	}
	
	/**
	 *  :: Find RevenueExpenseType by ID
	 *  @param id
	 *  @return RevenueExpenseType
	 * */
	public RevenueExpenseType findById(Long id) {
		typeDao = new RevenueExpenseTypeDaoImpl(context);
	    return typeDao.findById(id);
	}
	
	/**
	 *  :: Assemble map of the entity with respective values
	 *  @param type <b>Entity RevenueExpenseType</b>
	 *  @return Map of fields with respectives values
	 * */
	protected ContentValues getMapValueField(RevenueExpenseType type){
		
		ContentValues values = null;
		
		values = new ContentValues();
	    values.put(RevenueExpenseType.COLUMN_DESCRIPTION, type.getDescription());
	    values.put(RevenueExpenseType.COLUMN_TYPE, type.getType());
	    values.put(RevenueExpenseType.COLUMN_REGISTER_DATE, type.getRegisterDate());	    
	    
	    return values;
	}
	
	/**
	 *  :: Verify if the RevenueExpenseType is associated the other entity
	 *  @param type
	 *  @return True if it used and false otherwise
	 * */
	protected Boolean usedEntity(RevenueExpenseType type){
		
		PlaceDaoImpl placeDao = new PlaceDaoImpl(context);
    	
    	Map<String,String> mapColumnValue = new HashMap<String, String>();
    	mapColumnValue.put(RevenueExpenseType.COLUMN_ID,String.valueOf(type.getId()));
    	
	    return !placeDao.existEntity(new Place(), mapColumnValue); 
		
	}
	
	/**
	 *  :: Validate the entity
	 *  @param type <b>Entity that will be validated</b>
	 *  @return True if validated and false otherwise
	 * */
	protected Boolean validate(RevenueExpenseType type){
		
		boolean valid = true;
		
		// verify if description is null or blank
		if(type.getDescription() == null || type.getDescription().trim().isEmpty()){
			
			// add message erro
			HaveControlManager.getInstance().addMessage(R.string.type_description);
			valid = false;
		}		
		
		// verify if type is null or blank
		if(type.getType() == null || type.getType().trim().isEmpty()){
			
			// add message erro
			HaveControlManager.getInstance().addMessage(R.string.type_revenue_expense);
			valid = false;
		}
				
		// verify  if registerDate is null or blank
		if(type.getRegisterDate() == null || type.getRegisterDate().trim().isEmpty()){
			
			// adiciona a mensagem de erro
			HaveControlManager.getInstance().addMessage(R.string.required_date);
			valid = false;
		}
		
		// verify if there is type in database
		if(existRevenueExpenseType(type)){
			HaveControlManager.getInstance().addMessage(R.string.exist_type);
			valid = false;
        }
			
		return valid;
		
	}
	
	/**
	 *  :: Verify if exist entity in database
	 *  @param type <b>Entity that will be verified</b>
	 *  @return True if exist and false otherwise
	 * */
	public boolean existRevenueExpenseType(RevenueExpenseType type){   	
    	
		typeDao = new RevenueExpenseTypeDaoImpl(context);
    	
    	Map<String,String> mapColumnValue = new HashMap<String, String>();
    	mapColumnValue.put("upper("+RevenueExpenseType.COLUMN_DESCRIPTION+")", type.getDescription().trim().toUpperCase());
    	mapColumnValue.put(RevenueExpenseType.COLUMN_TYPE, type.getType());
    	
	    return typeDao.existEntity(type, mapColumnValue);    	
    }
}
