package havecontrol.com.br.havecontrol.entity;

import java.io.Serializable;

import br.com.havecontrol.common.enums.CostType;

/**
 * @author Fred
 * 
 */
public class Cost extends EntityBase implements Serializable {

	private static final long serialVersionUID = 3533066822473440628L;

	public static final String TABLE = Cost.class.getSimpleName().toUpperCase();
	public static final String COLUMN_ID = TABLE + "_ID";
	public static final String COLUMN_ID_FATHER = TABLE + "_IDFATHER";
	public static final String COLUMN_PRICE = TABLE + "_PRICE";
	public static final String COLUMN_PAYMENT_TYPE = TABLE + PaymentType.PAYMENT_TYPE;
	public static final String COLUMN_AMOUNT = TABLE + PaymentType.AMOUNT;
	public static final String COLUMN_DATE_START_PAYMENT = TABLE + PaymentType.DATE_START_PAYMENT;
	public static final String COLUMN_DATE_START = TABLE + "_DATESTART";
	public static final String COLUMN_DATE_END = TABLE + "_DATEEND";
	public static final String COLUMN_DATE_EXPIRATION = TABLE + "_DATEEXPIRATION";
	public static final String COLUMN_COST_TYPE = TABLE + "_COSTTYPE";
	public static final String COLUMN_OBSERVATION = TABLE + "_OBSERVATION";
	public static final String COLUMN_REGISTER_DATE = TABLE + "_REGISTERDATE";
	public static final String COLUMN_PAID = TABLE + "_PAID";

	private Cost costFather;
	private RevenueExpenseType revenueExpenseType;
	private Place place;
	private PlaceMaps placeMap;
	private Double price;
	private PaymentType paymentType;
	private String dateStart;
	private String dateEnd;
	private CostType costType;
	private String dateExpiration;
	private String observation;
	private String registerDate;
	private String paid;

	@Override
	public String getDescription() {
		return place.getDescription() + " " + price;
	}

	public Cost getCostFather() {
		return costFather;
	}

	public void setCostFather(Cost costFather) {
		this.costFather = costFather;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public PlaceMaps getPlaceMap() {
		return placeMap;
	}

	public void setPlaceMap(PlaceMaps placeMap) {
		this.placeMap = placeMap;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public CostType getCostType() {
		return costType;
	}

	public void setCostType(CostType costType) {
		this.costType = costType;
	}

	public String getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(String dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getPaid() {
		return paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
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

	@Override
	public String nameTable() {
		return TABLE;
	}

	@Override
	public String columnId() {
		return Cost.COLUMN_ID;
	}

}