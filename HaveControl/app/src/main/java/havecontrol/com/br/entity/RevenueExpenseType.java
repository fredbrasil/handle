package havecontrol.com.br.havecontrol.entity;

import java.io.Serializable;
import java.util.List;


/**
 * @author Fred
 *
 */
public class RevenueExpenseType extends EntityBase implements Serializable{

	private static final long serialVersionUID = -8926744643522952506L;
	
	public static final String TABLE = RevenueExpenseType.class.getSimpleName().toUpperCase();
	public static final String COLUMN_ID = TABLE+"_ID";
	public static final String COLUMN_DESCRIPTION = TABLE+"_DESCRIPTION";
	public static final String COLUMN_TYPE = TABLE+"_TYPE";
	public static final String COLUMN_REGISTER_DATE = TABLE+"_REGISTERDATE";
	
	private String description;
	private String type;
	private String registerDate;
	private List<Place> places;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	@Override
	public String nameTable() {
		return TABLE;
	}

	@Override
	public String columnId() {
		return RevenueExpenseType.COLUMN_ID;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
	
 } 