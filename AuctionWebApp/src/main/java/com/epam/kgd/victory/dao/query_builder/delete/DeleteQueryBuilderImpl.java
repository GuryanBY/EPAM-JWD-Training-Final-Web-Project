package com.epam.kgd.victory.dao.query_builder.delete;

public class DeleteQueryBuilderImpl implements DeleteQueryBuilder {

	private final static DeleteQueryBuilder INSTANCE = new DeleteQueryBuilderImpl();

	private final static String MAIN_PART = "DELETE FROM ";
	private final static String WHERE_PART = " WHERE ";
	private final static String AND_PART = " AND ";

	private DeleteQueryBuilderImpl() {
	}

	public static DeleteQueryBuilder getInstance() {
		return INSTANCE;
	}

	@Override
	public String getDeleteQuery(String fromTable, String... criteria) {

		StringBuilder result = new StringBuilder();
		result = result.append(MAIN_PART).append(fromTable);

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
