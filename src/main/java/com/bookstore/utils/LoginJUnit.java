package com.bookstore.utils;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 模拟登陆测试类
 * @Author ME495
 */
public abstract class LoginJUnit extends MockMvcJUnit{

    private MockHttpSession mockHttpSession;

    protected MockHttpSession getMockHttpSession() {
        return mockHttpSession;
    }

    /**
     * 模拟用户登陆，返回登陆信息，并保存session
     * @param userName 用户账号
     * @param password 密码
     * @return 登陆信息，json数据
     * @throws UnsupportedEncodingException
     */
    protected String userLogin(String userName, String password) throws UnsupportedEncodingException {
        MvcResult result = null;
        try {
            result = getMockMvc().perform(
                    post("/user_login.do")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("user_name", userName)
                            .param("password", password))
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mockHttpSession = (MockHttpSession) result.getRequest().getSession();
        return result.getResponse().getContentAsString();
    }

    /**
     * 管理员登陆，返回登陆信息，并保存session
     * @param adminName
     * @param password
     * @return
     * @throws UnsupportedEncodingException
     */
    protected String adminLogin(String adminName, String password) throws UnsupportedEncodingException {
        MvcResult result = null;
        try {
            result = getMockMvc().perform(
                    post("/admin_login.do")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("admin_name", adminName)
                            .param("password", password))
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mockHttpSession = (MockHttpSession) result.getRequest().getSession();
        return result.getResponse().getContentAsString();
    }

    protected String superLogin(String password) throws UnsupportedEncodingException {
        MvcResult result = null;
        try {
            result = getMockMvc().perform(
                    post("/super_login.do")
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                            .param("password", password))
                    .andExpect(status().isOk())
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mockHttpSession = (MockHttpSession) result.getRequest().getSession();
        return result.getResponse().getContentAsString();
    }
}
