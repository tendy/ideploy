package io.ideploy.web;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author code4china
 * @description
 * @date Created in 11:36 2018/12/7
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IDeployWebApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractContextTest {

    @Autowired
    protected ApplicationContext applicationContext;

}
