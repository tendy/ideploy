package io.ideploy.web.controller;

import io.ideploy.web.AbstractContextTest;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author code4china
 * @description
 * @date Created in 10:52 2018/12/7
 */
public class ControllerBaseTest extends AbstractContextTest {

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mockMvc;

    @Before
    public void before() throws Exception {

    }


}
