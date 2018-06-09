package com.bookstore.testsuite;

import com.bookstore.controller.test.*;
import com.bookstore.mapper.test.OrderDetailMapperTest;
import com.bookstore.service.test.OrderManagerServiceTest;
import com.bookstore.service.test.UserServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.bookstore.mapper.test.AdminMapperTest;
import com.bookstore.mapper.test.OrderMapperTest;
import com.bookstore.mapper.test.UserMapperTest;
import com.bookstore.message.test.ResponseMesTest;
import com.bookstore.service.test.SuperServiceTest;
import com.bookstore.service.test.LoginServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
	LoginControllerTest.class,
    LogoutControllerTest.class,
    OrderManagerControllerTest.class,
    SuperControllerTest.class,
    UserControllerTest.class,
    AdminMapperTest.class,
    OrderDetailMapperTest.class,
    OrderMapperTest.class,
    UserMapperTest.class,
    ResponseMesTest.class,
    LoginServiceTest.class,
    OrderManagerControllerTest.class,
    SuperServiceTest.class,
    UserServiceTest.class
})
public class AllTests {
	
}
