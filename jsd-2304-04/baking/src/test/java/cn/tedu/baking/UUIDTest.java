package cn.tedu.baking;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import java.util.UUID;

public class UUIDTest {
    @Test
    void uuid() {
        for (int i = 0; i < 10; i++) {
            System.out.println(UUID.randomUUID());
        }
    }

    @Test
    void md5() {
        String salt = "887kjlsddsfjkhkjhsf";
        String rawPassword = "1"; // P@ssw0rd123456
        for (int i = 0; i < 10; i++) {
            String encodedPassword
                    = DigestUtils.md5DigestAsHex(
                    (salt + salt + rawPassword + salt).getBytes()
            );
            System.out.println(encodedPassword);
        }
        // e10adc3949ba59abbe56e057f20f883e

        // d7272a248c45a97fb50c06d244be7661
    }

    @Test
    void bcrypt() {
        BCryptPasswordEncoder encoder
                = new BCryptPasswordEncoder(15);
        String rawPassword = "123456";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 13; i++) {
            //String encodedPassword =
            encoder.encode(rawPassword);
            //System.out.println(encodedPassword);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
