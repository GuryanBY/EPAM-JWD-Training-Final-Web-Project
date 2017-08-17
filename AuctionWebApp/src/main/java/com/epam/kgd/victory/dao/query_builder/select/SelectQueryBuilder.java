package com.epam.kgd.victory.dao.query_builder.select;

public interface SelectQueryBuilder {
	String getSelectQuery(String queryResult, String fromTable, String... criteria );

}
