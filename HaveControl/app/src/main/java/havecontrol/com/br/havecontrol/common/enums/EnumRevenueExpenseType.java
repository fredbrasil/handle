package havecontrol.com.br.havecontrol.common.enums;

public enum EnumRevenueExpenseType {

	REVENUE("R","Revenue"),
	EXPENSE("E","Expense");
	
	private String code;
	private String name;
	
	private EnumRevenueExpenseType(String code,String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
}
