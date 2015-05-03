package havecontrol.com.br.havecontrol.entity;

import java.io.Serializable;

public abstract class EntityBase implements Serializable{
		
	private static final long serialVersionUID = 6250242178998946592L;
	
	private Long id;
	public static final String NAME_TABLE = "nameTable";
	public static final String COLUMN_ID = "columnId";

	public EntityBase() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public abstract String nameTable();
	public abstract String columnId();
	
	public abstract String getDescription();
}

