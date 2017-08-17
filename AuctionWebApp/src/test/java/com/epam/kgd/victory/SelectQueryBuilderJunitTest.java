package com.epam.kgd.victory;

import static com.epam.kgd.victory.dao.query_builder.UserTable.*;

import com.epam.kgd.victory.dao.query_builder.select.SelectQueryBuilder;
import com.epam.kgd.victory.dao.query_builder.select.SelectQueryBuilderImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class SelectQueryBuilderJunitTest {
	private static  SelectQueryBuilder selectBuilder;
	private String expectedQuery = "SELECT `u_id` FROM `user` WHERE `u_login`=?"; 
	
	@BeforeClass
	public static void initBuilder() {
		selectBuilder = SelectQueryBuilderImpl.getInstance();
	}

	@Test
	public void selectBuilderTest() {
		String query = selectBuilder.getSelectQuery(USER_ID.getValue(), USER_TABLE_NAME.getValue(), BY_LOGIN.getValue());				;

		Assert.assertEquals(expectedQuery, query);
	}	

}
