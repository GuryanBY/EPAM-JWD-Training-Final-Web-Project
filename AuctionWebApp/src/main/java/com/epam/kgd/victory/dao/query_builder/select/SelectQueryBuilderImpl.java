package com.epam.kgd.victory.dao.query_builder.select;

public class SelectQueryBuilderImpl implements SelectQueryBuilder {
	private final static SelectQueryBuilderImpl INSTANCE = new SelectQueryBuilderImpl();

	private final static String SELECT_PART = "SELECT ";
	private final static String FROM_PART = " FROM ";
	private final static String WHERE_PART = " WHERE ";
	private final static String AND_PART = " AND ";
	
	private SelectQueryBuilderImpl() {
	}

	public static SelectQueryBuilder getInstance() {
		return INSTANCE;
	}

	@Override
	public String getSelectQuery(String queryResult, String fromTable, String... criteria) {

		StringBuilder result = new StringBuilder();
		result = result.append(SELECT_PART).append(queryResult).append(FROM_PART).append(fromTable);

		if (criteria.length == 1) {
			result = result.append(WHERE_PART).append(criteria[0]);
		} else if (criteria.length > 1) {
			String multiWhereQuery = "";
			
			for (int i = 0; i < criteria.length - 1; i++) {
				multiWhereQuery = multiWhereQuery.concat(AND_PART.concat(criteria[i + 1]));
			}
			result = result.append(WHERE_PART).append(criteria[0]).append(multiWhereQuery);
		}

		return result.toString();
	}

}
