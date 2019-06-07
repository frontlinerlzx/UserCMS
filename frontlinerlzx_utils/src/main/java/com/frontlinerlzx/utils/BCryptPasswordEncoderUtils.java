package com.frontlinerlzx.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);

    }

    public static void main(String[] args) {
        String password="admin";


        String aa = encodePassword(password);

        //$2a$10$WqGgmg4.aMMVHQouP.ESf.OIXE2ZGAVWIvqq5VsdT2rY3eQLQ7stG
        //$2a$10$Ba9ASJYO50EgGUFfNxWdRuptdB3w0rnK1T8pS8gTGTHs3LuS7k2qK
        System.out.println(aa);
    }


}
