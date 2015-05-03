package havecontrol.com.br.havecontrol.common.enums;

import br.com.havecontrol.R;

public enum HaveControlMenu {

	/* Color and icon of R.java */
	
	DASHBOARD(1,"Dashboard",R.color.red_dark,R.drawable.ic_dashboard),
	CATEGORY(2,"Category",R.color.red_light,R.drawable.ic_category),
	PLACE(3,"Place",R.color.blue_dark,R.drawable.ic_place),
	COST(4,"Cost",R.color.blue_light,R.drawable.ic_cost),
	GRAPHIC(5,"Graphic",R.color.orange_dark,R.drawable.ic_chart),
	MAP(6,"Map",R.color.orange_light,R.drawable.ic_location_place1),
	SETTINGS(7,"Settings",R.color.green_dark,R.drawable.ic_setting);
	
	private int code;
	private String name;
	private int color;
	private int icon;
	
	private HaveControlMenu(int code,String name,int color,int icon) {
		this.code = code;
		this.name = name;
		this.color = color;
		this.icon = icon;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	public int getColor() {
		return color;
	}
	public int getIcon() {
		return icon;
	}
}
