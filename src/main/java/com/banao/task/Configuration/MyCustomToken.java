package com.banao.task.Configuration;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class MyCustomToken {
    private final String SECRET_KEY = "root@123";

    public String generateKey(String email) {
        email = SECRET_KEY + email;
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(email.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean validateKey(String email, String hash) {
        String key = generateKey(email);
        System.out.println(hash + "  matches " + key);
        if (hash.equals(key)) {
            System.out.println("Matched Sucessfully");
            return true;
        } else {
            System.out.println("Match Unsucessfull");
            return false;
        }
    }
    public String getVerificationLink(String email)
    {
        String hash = generateKey(email);
        return "http://localhost:8080/verify-user?email="+email+"&token="+hash;
    }
}
