package havecontrol.com.br.havecontrol.common;

import br.com.havecontrol.entity.Cost;
import br.com.havecontrol.entity.Place;
import br.com.havecontrol.entity.PlaceMaps;
import br.com.havecontrol.entity.RevenueExpenseType;

public class ConstantDatabase {

	public static final int DATABASE_VERSION = 2;
	public static final String DATABASE_NAME = "haveControl.db";
	
	
	public static String createTableTipo() {
		
		StringBuilder sql = new StringBuilder();
		
		/*  TABLE: RevenueExpenseType */
		sql.append(" CREATE TABLE IF NOT EXISTS ")
			.append(RevenueExpenseType.TABLE)
			.append(" (")
					.append(RevenueExpenseType.COLUMN_ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
					.append(RevenueExpenseType.COLUMN_DESCRIPTION).append(" TEXT NOT NULL, ")
					.append(RevenueExpenseType.COLUMN_TYPE).append(" TEXT NOT NULL, ")
					.append(RevenueExpenseType.COLUMN_REGISTER_DATE).append(" TEXT NOT NULL ")
			.append("); ");
		
		return sql.toString();
		
	}

	public static String createTablePlace() {
		
		StringBuilder sql = new StringBuilder();
		
		/* TABLE: PLACE */
		sql.append(" CREATE TABLE IF NOT EXISTS ")
		.append(Place.TABLE)
		.append(" (")
				.append(Place.COLUMN_ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
				.append(Place.COLUMN_DESCRIPTION).append(" TEXT NOT NULL, ")
				.append(Place.COLUMN_REGISTER_DATE).append(" TEXT NOT NULL, ")
				.append(RevenueExpenseType.COLUMN_ID).append(" INTEGER NOT NULL, FOREIGN KEY ( ")
									   .append(RevenueExpenseType.COLUMN_ID).append(") REFERENCES ")
									   .append(RevenueExpenseType.TABLE).append(" (").append(RevenueExpenseType.COLUMN_ID).append(" )")
		.append("); ");
		
		return sql.toString();
	}
	
	public static String createTablePlaceMaps() {
		
		StringBuilder sql = new StringBuilder();
		
		/*  TABLE: PLACEMAPS 	*/		
		sql.append(" CREATE TABLE IF NOT EXISTS ")
			.append(PlaceMaps.TABLE)
			.append(" (")
					.append(PlaceMaps.COLUMN_ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
					.append(PlaceMaps.COLUMN_DESCRIPTION).append(" TEXT NOT NULL, ")
					.append(PlaceMaps.COLUMN_LATITUDE).append(" REAL NOT NULL, ")
					.append(PlaceMaps.COLUMN_LONGITUDE).append(" REAL NOT NULL, ")
					.append(PlaceMaps.COLUMN_REGISTER_DATE).append(" TEXT NOT NULL, ")
					.append(Place.COLUMN_ID).append(" INTEGER NOT NULL, FOREIGN KEY ( ")
									   .append(Place.COLUMN_ID).append(") REFERENCES ")
									   .append(Place.TABLE).append(" (").append(Place.COLUMN_ID).append(" )")
			.append("); ");
		
		return sql.toString();
	}
	
	
	public static String createTableCost() {
		
		StringBuilder sql = new StringBuilder();
		
		/* TABLE: COST */ 
		sql.append(" CREATE TABLE IF NOT EXISTS ")
		.append(Cost.TABLE)
		.append(" (")
				.append(Cost.COLUMN_ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
				.append(Cost.COLUMN_ID_FATHER).append(" INTERGER, ")
				.append(Cost.COLUMN_DATE_START).append(" TEXT, ")
				.append(Cost.COLUMN_DATE_END).append(" TEXT, ")
				.append(Cost.COLUMN_DATE_EXPIRATION).append(" TEXT, ")
				.append(Cost.COLUMN_PAYMENT_TYPE).append(" TEXT NOT NULL, ")
				.append(Cost.COLUMN_COST_TYPE).append(" TEXT, ")
				.append(Cost.COLUMN_PRICE).append(" REAL NOT NULL, ")				
				.append(Cost.COLUMN_AMOUNT).append(" INTEGER, ")
				.append(Cost.COLUMN_DATE_START_PAYMENT).append(" TEXT, ")				
				.append(Cost.COLUMN_PAID).append(" TEXT, ")
				.append(Cost.COLUMN_OBSERVATION).append(" TEXT, ")
				.append(Cost.COLUMN_REGISTER_DATE).append(" TEXT NOT NULL, ")
				.append(RevenueExpenseType.COLUMN_ID).append(" INTEGER NOT NULL, ")
				.append(Place.COLUMN_ID).append(" INTEGER NOT NULL, ")
				.append(PlaceMaps.COLUMN_ID).append(" INTEGER, ")
									   .append(" FOREIGN KEY ( ").append(Cost.COLUMN_ID_FATHER).append(") REFERENCES ")
									   .append(Cost.TABLE).append(" (").append(Cost.COLUMN_ID).append(" ),")									   
									   .append(" FOREIGN KEY ( ").append(RevenueExpenseType.COLUMN_ID).append(") REFERENCES ")
									   .append(RevenueExpenseType.TABLE).append(" (").append(RevenueExpenseType.COLUMN_ID).append(" ),")				
									   .append(" FOREIGN KEY ( ").append(Place.COLUMN_ID).append(") REFERENCES ")
									   .append(Place.TABLE).append(" (").append(Place.COLUMN_ID).append(" ),")				
									   .append(" FOREIGN KEY ( ").append(PlaceMaps.COLUMN_ID).append(") REFERENCES ")
									   .append(PlaceMaps.TABLE).append(" (").append(PlaceMaps.COLUMN_ID).append(" )")
		.append("); ");
		
		return sql.toString();
	}
	
	public static String createFK_Place_RevenueExpenseType() {
		
		StringBuilder sql = new StringBuilder();
		
		/* TRIGGER: FK_PLACE_REVENUEEXPENSETYPE */
		sql.append(" CREATE TRIGGER fk_place_revenueexpensetype ")
		.append(" BEFORE INSERT ON ").append(Place.TABLE)
		.append(" FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT ")
		.append(RevenueExpenseType.COLUMN_ID).append(" FROM ").append(RevenueExpenseType.TABLE)
		.append(" WHERE ").append(RevenueExpenseType.COLUMN_ID).append(" = new.").append(RevenueExpenseType.COLUMN_ID)
		.append(" ) IS NULL) THEN RAISE (ABORT,'Foreign Key Violation') END; ")
		.append(" END; ");
		
		return sql.toString();
	}	

	public static String createFK_PlaceMaps_Place() {
		
		StringBuilder sql = new StringBuilder();
		
		/* TRIGGER: FK_PLACEMAPS_PLACE */ 
		sql.append(" CREATE TRIGGER fk_placemaps_place ")
		.append(" BEFORE INSERT ON ").append(PlaceMaps.TABLE)
		.append(" FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT ")
		.append(Place.COLUMN_ID).append(" FROM ").append(Place.TABLE)
		.append(" WHERE ").append(Place.COLUMN_ID).append(" = new.").append(Place.COLUMN_ID)
		.append(" ) IS NULL) THEN RAISE (ABORT,'Foreign Key Violation') END; ")
		.append(" END; ");
		
		return sql.toString();
	}
	
	
	public static String createFK_Cost_RevenueExpenseType() {
		
		StringBuilder sql = new StringBuilder();
		
		/* TRIGGER: FK_COST_REVENUEEXPENSETYPE */ 
		sql.append(" CREATE TRIGGER fk_cost_revenueexpensetype ")
		.append(" BEFORE INSERT ON ").append(Cost.TABLE)
		.append(" FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT ")
		.append(RevenueExpenseType.COLUMN_ID).append(" FROM ").append(RevenueExpenseType.TABLE)
		.append(" WHERE ").append(RevenueExpenseType.COLUMN_ID).append(" = new.").append(RevenueExpenseType.COLUMN_ID)
		.append(" ) IS NULL) THEN RAISE (ABORT,'Foreign Key Violation') END; ")
		.append(" END; ");
		
		return sql.toString();
	}
	
	public static String createFK_Cost_Place() {
		
		StringBuilder sql = new StringBuilder();
		
		/* TRIGGER: FK_COST_PLACE */
		sql.append(" CREATE TRIGGER fk_cost_place ")
		.append(" BEFORE INSERT ON ").append(Cost.TABLE)
		.append(" FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT ")
		.append(Place.COLUMN_ID).append(" FROM ").append(Place.TABLE)
		.append(" WHERE ").append(Place.COLUMN_ID).append(" = new.").append(Place.COLUMN_ID)
		.append(" ) IS NULL) THEN RAISE (ABORT,'Foreign Key Violation') END; ")
		.append(" END; ");
		
		return sql.toString();
	}
	
	public static String dropFK_Cost_Place() {
		
		StringBuilder sql = new StringBuilder();
		
		/* TRIGGER: FK_COST_PLACE */ 
		sql.append(" DROP TRIGGER IF EXISTS ").append("fk_cost_place; ");
		
		return sql.toString();
	}
	
	public static String dropFK_Cost_RevenueExpenseType() {
		
		StringBuilder sql = new StringBuilder();
		
		/* TRIGGER: FK_COST_REVENUEEXPENSETYPE */ 
		sql.append(" DROP TRIGGER IF EXISTS ").append("fk_cost_revenueexpensetype; ");
		
		return sql.toString();
	}

	public static String dropFK_PlaceMaps_Place() {
		
		StringBuilder sql = new StringBuilder();
		
		/* TRIGGER: FK_PLACEMAPS_PLACE */ 
		sql.append(" DROP TRIGGER IF EXISTS ").append("fk_placemaps_place; ");
		
		return sql.toString();
	}

	public static String dropFK_Place_RevenueExpenseType() {
		
		StringBuilder sql = new StringBuilder();
		
		/* TRIGGER: FK_PLACE_REVENUEEXPENSETYPE */ 
		sql.append(" DROP TRIGGER IF EXISTS ").append("fk_place_revenueexpensetype; ");
		
		return sql.toString();
	}
	
	public static String dropTableRevenueExpenseType() {
		
		StringBuilder sql = new StringBuilder();
		
		/* TABLE: RevenueExpenseType */
		sql.append(" DROP TABLE IF EXISTS ").append(RevenueExpenseType.TABLE).append("; ");
		
		return sql.toString();
	}	

	public static String dropTablePlace() {
		
		StringBuilder sql = new StringBuilder();
		
		/* TABLE: PLACE */ 
		sql.append(" DROP TABLE IF EXISTS ").append(Place.TABLE).append("; ");
		
		return sql.toString();
	}
	
	public static String dropTablePlaceMaps() {
		
		StringBuilder sql = new StringBuilder();
		
		/* TABLE: PLACEMAPS */
		sql.append(" DROP TABLE IF EXISTS ").append(PlaceMaps.TABLE).append("; ");
		
		return sql.toString();
	}
	
	public static String dropTableCost() {
		
		StringBuilder sql = new StringBuilder();
		
		/* TABLE: Cost */
		sql.append(" DROP TABLE IF EXISTS ").append(Cost.TABLE).append("; ");
		
		return sql.toString();
	}
}
