package havecontrol.com.br.havecontrol.service;



public interface BaseService<T>{

	void insert(T entity);
	
	void delete(T entity);

	void update(T entity);
	
}
