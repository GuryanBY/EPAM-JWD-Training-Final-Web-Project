package com.epam.kgd.victory;

import static com.epam.kgd.victory.dao.query_builder.UserTable.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.kgd.victory.dao.query_builder.update.UpdateQueryBuilder;
import com.epam.kgd.victory.dao.query_builder.update.UpdateQueryBuilderImpl;

public class UpdateQueryBuilderJUnitTest {
	private static UpdateQueryBuilder updateBuilder;
	private String expectedQuery ="UPDATE `user` SET `u_role_id`=?, `u_login`=?, `u_password`=?, `u_first_name`=?, `u_last_name`=?, `u_email`=?, `u_phone`=?, `u_registr_date`=?, `u_status`=? WHERE `u_id`=?";
	
	@BeforeClass
	public static void initBuilder() {
		updateBuilder = UpdateQueryBuilderImpl.getInstance();
	}

	@Test
	public void selectBuilderTest() {
		String query = updateBuilder.getUpdateQuery(USER_TABLE_NAME.getValue(), USER_UPDATE_ENTITY.getValue(),
				BY_ID.getValue());				

		Assert.assertEquals(expectedQuery, query);
	}	

}
