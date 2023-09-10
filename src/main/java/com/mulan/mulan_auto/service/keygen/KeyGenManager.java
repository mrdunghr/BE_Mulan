package com.mulan.mulan_auto.service.keygen;

import java.security.SecureRandom;
import java.util.Random;

public class KeyGenManager {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_-+=<>?";
    public String generateRandomKey(int length){
        Random random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++){
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
//
//    public static void main(String[] args) {
//        String randomString = generateRandomKey(50);
//        System.out.println("Random String: " + randomString);
//    }
}
