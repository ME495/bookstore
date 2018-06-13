package com.bookstore.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.bookstore.controller.test.AdminControllerTest;
import com.bookstore.controller.test.CommonControllerTest;
import com.bookstore.controller.test.LoginControllerTest;
import com.bookstore.controller.test.LogoutControllerTest;
import com.bookstore.controller.test.OrderManagerControllerTest;
import com.bookstore.controller.test.SuperControllerTest;
import com.bookstore.controller.test.UserControllerTest;
import com.bookstore.mapper.test.AdminMapperTest;
import com.bookstore.mapper.test.CommonMapperTest;
import com.bookstore.mapper.test.OrderDetailMapperTest;
import com.bookstore.mapper.test.OrderMapperTest;
import com.bookstore.mapper.test.SuperMapperTest;
import com.bookstore.mapper.test.UserMapperTest;
import com.bookstore.message.test.ResponseMesTest;
import com.bookstore.service.test.AdminServiceTest;
import com.bookstore.service.test.CommonServiceTest;
import com.bookstore.service.test.LoginServiceTest;
import com.bookstore.service.test.SuperServiceTest;
import com.bookstore.service.test.UserServiceTest;

@RunWith(Suite.class)
@SuiteClasses({
	LoginControllerTest.class,
    LogoutControllerTest.class,
    OrderManagerControllerTest.class,
    SuperControllerTest.class,
    AdminControllerTest.class,
    UserControllerTest.class,
    CommonControllerTest.class,
    
    LoginServiceTest.class,
    SuperServiceTest.class,
    AdminServiceTest.class,
    UserServiceTest.class,
    CommonServiceTest.class,
    
    OrderDetailMapperTest.class,
    OrderMapperTest.class,
    SuperMapperTest.class,
    AdminMapperTest.class,
    UserMapperTest.class,
    CommonMapperTest.class,
    
    ResponseMesTest.class
})
public class AllTests {
}
