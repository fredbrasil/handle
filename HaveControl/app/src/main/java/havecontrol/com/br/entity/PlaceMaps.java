package havecontrol.com.br.havecontrol.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author Fred
 *
 */
public class PlaceMaps extends EntityBase implements Serializable{

	private static final long serialVersionUID = -43252339393277173L;
	
	public static final String TABLE = PlaceMaps.class.getSimpleName().toUpperCase();
	public static final String COLUMN_ID = TABLE+"_ID";
	public static final String COLUMN_DESCRIPTION = TABLE+"_DESCRIPTION";	
	public static final String COLUMN_REGISTER_DATE = TABLE+"_REGISTERDATE";	
	public static final String COLUMN_LATITUDE = TABLE+"_LATITUDE";
	public static final String COLUMN_LONGITUDE = TABLE+"_LONGITUDE";
	
	private String description;
	private Place place;
	private Double latitude;
	private Double longitude;
	private String registerDate;

	public PlaceMaps() {
		// TODO Auto-generated constructor stub
	}
	
	public PlaceMaps(String description,Double latitude,Double longitude,String registerDate) {
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.registerDate = registerDate;
	}
	
	public PlaceMaps(String description,Double latitude,Double longitude) {
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
		this.registerDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	}
	
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

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String nameTable() {
		return TABLE;
	}

	@Override
	public String columnId() {
		return PlaceMaps.COLUMN_ID;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
	
 } 