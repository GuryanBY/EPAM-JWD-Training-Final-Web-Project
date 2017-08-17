package com.epam.kgd.victory.dao.query_builder.delete;

public interface DeleteQueryBuilder {
	String getDeleteQuery(String fromTable, String... criteria);

}
