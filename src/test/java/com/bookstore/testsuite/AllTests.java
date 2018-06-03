package com.bookstore.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.bookstore.controller.test.LoginControllerTest;
import com.bookstore.controller.test.LogoutControllerTest;
import com.bookstore.mapper.test.AdminMapperTest;
import com.bookstore.mapper.test.UserMapperTest;
import com.bookstore.message.test.ResponseMesTest;
import com.bookstore.service.test.AdminServiceTest;
import com.bookstore.service.test.LoginServiceTest;

import junit.framework.JUnit4TestAdapter;
import junit.framework.Test;
import junit.framework.TestSuite;

@RunWith(Suite.class)
@SuiteClasses({
	LoginControllerTest.class,
	AdminMapperTest.class,
	UserMapperTest.class,
	ResponseMesTest.class,
	AdminServiceTest.class,
	LoginServiceTest.class,
	LogoutControllerTest.class
})
public class AllTests {
	
}
