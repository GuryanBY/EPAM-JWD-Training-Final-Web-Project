package com.epam.kgd.victory.dao.query_builder.update;

public interface UpdateQueryBuilder {
	
	String getUpdateQuery(String table,String entity, String... criteria);

}
