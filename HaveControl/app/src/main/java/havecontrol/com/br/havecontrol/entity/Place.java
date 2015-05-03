package havecontrol.com.br.havecontrol.entity;

import java.io.Serializable;
import java.util.List;


/**
 * @author Fred
 *
 */
public class Place extends EntityBase implements Serializable{

	private static final long serialVersionUID = -6013116474210882432L;
	
	public static final String TABLE = Place.class.getSimpleName().toUpperCase();
	public static final String COLUMN_ID = TABLE+"_ID";
	public static final String COLUMN_DESCRIPTION = TABLE+"_DESCRIPTION";	
	public static final String COLUMN_REGISTER_DATE = TABLE+"_REGISTERDATE";	
	
	private String description;
	private RevenueExpenseType revenueExpenseType;
	private String registerDate;
	private List<PlaceMaps> placeMaps;

	@Override
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public RevenueExpenseType getRevenueExpenseType() {
		return revenueExpenseType;
	}

	public void setRevenueExpenseType(RevenueExpenseType revenueExpenseType) {
		this.revenueExpenseType = revenueExpenseType;
	}

	public List<PlaceMaps> getPlaceMaps() {
		return placeMaps;
	}

	public void setPlaceMaps(List<PlaceMaps> placeMaps) {
		this.placeMaps = placeMaps;
	}

	@Override
	public String nameTable() {
		return TABLE;
	}

	@Override
	public String columnId() {
		return Place.COLUMN_ID;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
	
 } 