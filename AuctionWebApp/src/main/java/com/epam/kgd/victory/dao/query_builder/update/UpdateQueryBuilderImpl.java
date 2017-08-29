package com.epam.kgd.victory.dao.query_builder.update;

public class UpdateQueryBuilderImpl implements UpdateQueryBuilder {
	private final static UpdateQueryBuilder INSTANCE = new UpdateQueryBuilderImpl();

	private final static String UPDATE_PART = "UPDATE ";
	private final static String SET_PART = " SET ";
	private final static String WHERE_PART = " WHERE ";
	private final static String AND_PART = " AND ";

	private UpdateQueryBuilderImpl() {

	}

	public static UpdateQueryBuilder getInstance() {
		return INSTANCE;
	}

	@Override
	public String getUpdateQuery(String table, String entity, String... criteria) {
		StringBuilder result = new StringBuilder();
		result = result.append(UPDATE_PART).append(table).append(SET_PART).append(entity);

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
