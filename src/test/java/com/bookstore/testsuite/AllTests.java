package com.bookstore.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.bookstore.controller.test.LoginControllerTest;
import com.bookstore.controller.test.LogoutControllerTest;
import com.bookstore.controller.test.OrderManagerControllerTest;
import com.bookstore.mapper.test.AdminMapperTest;
import com.bookstore.mapper.test.OrderMapperTest;
import com.bookstore.mapper.test.UserMapperTest;
import com.bookstore.message.test.ResponseMesTest;
import com.bookstore.service.test.SuperServiceTest;
import com.bookstore.service.test.LoginServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
	LoginControllerTest.class,
	AdminMapperTest.class,
	UserMapperTest.class,
	ResponseMesTest.class,
	SuperServiceTest.class,
	LoginServiceTest.class,
	LogoutControllerTest.class,
	OrderMapperTest.class,
	OrderManagerControllerTest.class,
	OrderManagerControllerTest.class
})
public class AllTests {
	
}
