package io.ideploy.web.security;

import java.util.UUID;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author code4china
 * @description
 * @date Created in 13:52 2019/1/28
 */
public class BCryptPasswordEncoderTest {


    @Test
    public void testCryptPassword()throws Exception{
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin"));
    }


}
