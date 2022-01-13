package com.liu.forfunboot.jwtTest;

import com.liu.forfunboot.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtTest {
    @Autowired
    private JwtUtils jwtUtils;


    @Test
    public void test1() {
        Base64.Encoder encoder = Base64.getEncoder();
        Base64.Decoder decoder = Base64.getDecoder();
        String text = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        String encodeString = encoder.encodeToString(bytes);
        String decodeString = new String(decoder.decode(encodeString), StandardCharsets.UTF_8);
        System.out.println(encodeString);
        System.out.println(decodeString);

    }
    @Test
    public void test2() {
        long timeLong = 1516239022L;
        Date date = new Date(timeLong);
        System.out.println(date.getYear());
        System.out.println(date.getMonth());
        System.out.println(date.getDate());
        DateTime dateTime = new DateTime(timeLong);
        System.out.println(dateTime.toString("yyyy-MM-DD hh:mm:ss sss"));

    }

    @Test
    public void test3() {
        Base64.Decoder decoder = Base64.getDecoder();
        String s1 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";
        String s2 = "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ";
        String s3 = "SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

        System.out.println(new String(decoder.decode(s1), StandardCharsets.UTF_8));
        System.out.println(new String(decoder.decode(s2), StandardCharsets.UTF_8));

    }

    @Test
    public void test4() {
        long uid = 1234L;
//        JwtUtils jwtUtils = new JwtUtils();
        String token = jwtUtils.generateToken(uid);
        Claims claim = jwtUtils.getClaimByToken(token);
        System.out.println(jwtUtils.isTokenExpired(new Date()));

    }

}
