package com.epam.kgd.victory;

import static com.epam.kgd.victory.dao.query_builder.UserTable.*;

import com.epam.kgd.victory.dao.query_builder.delete.DeleteQueryBuilder;
import com.epam.kgd.victory.dao.query_builder.delete.DeleteQueryBuilderImpl;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DeleteQueryBuilderJunitTest {
	private static  DeleteQueryBuilder deleteBuilder;
	private String expectedQuery = "DELETE FROM `user` WHERE `u_id`=? AND `u_email`=? AND `u_login`=?";

	@BeforeClass
	public static void initBuilder() {
		deleteBuilder = DeleteQueryBuilderImpl.getInstance();
	}

	@Test
	public void deleteBuilderTest() {
		String query = deleteBuilder.getDeleteQuery(USER_TABLE_NAME.getValue(), BY_ID.getValue(), BY_EMAIL.getValue(),
				BY_LOGIN.getValue());

		Assert.assertEquals(expectedQuery, query);
	}

}
