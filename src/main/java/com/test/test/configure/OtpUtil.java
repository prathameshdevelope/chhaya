package com.test.test.configure;

import java.security.SecureRandom;

public class OtpUtil {

    private static final SecureRandom random = new SecureRandom();

    private OtpUtil() {}

    public static Integer generateOtp() {
        return 100000 + random.nextInt(900000); 
    }
}