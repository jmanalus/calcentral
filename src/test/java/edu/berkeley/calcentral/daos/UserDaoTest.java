/*
 * Licensed to the Sakai Foundation (SF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The SF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package edu.berkeley.calcentral.daos;

import edu.berkeley.calcentral.DatabaseAwareTest;
import edu.berkeley.calcentral.domain.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Random;

public class UserDaoTest extends DatabaseAwareTest {

	private static final Log LOGGER = LogFactory.getLog(UserDaoTest.class);

	private String uid;

	@Autowired
	private UserDao dao;

	@Before
	public void setup() {
		uid = String.valueOf(new Random().nextLong());
	}

	@Test
	public void get() throws Exception {
		try {
			dao.get("2040");
		} catch (EmptyResultDataAccessException e) {
			// once authorized, user is created
			dao.insert("2040", "Oliver Heyer");
			UserDetails details = dao.getUserDetails("2040");
			assertNotNull(details);
		}

		// now he should exist
		User user = dao.get("2040");
		LOGGER.info(user);
		assertEquals("Oliver Heyer", user.getPreferredName());
	}

	@Test
	public void update() throws Exception {
		//TODO: fill me in
	}

	@Test
	public void delete() throws Exception {
		//TODO: fill me in
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void getNonExistent() throws Exception {
		dao.get("0000000");
	}

	@Test
	public void insertUser() throws Exception {
		dao.insert(uid, "Jane Random");
		UserDetails user = dao.get(uid);
		assertNotNull(user);
	}

}
