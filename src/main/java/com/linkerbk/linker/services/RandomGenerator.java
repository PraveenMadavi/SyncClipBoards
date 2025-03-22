package com.linkerbk.linker.services;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;
@Service
public class RandomGenerator {

    private static final SecureRandom random = new SecureRandom();
    private static final Set<Integer> generatedNumbers = new HashSet<>();

    public static int generateUnique6DigitNumber() {
        int ranNum;
        do {
            ranNum = 100_000 + random.nextInt(900_000); // Generates a number between 100000 - 999999
        } while (!generatedNumbers.add(ranNum)); // Ensure uniqueness
        System.out.println("Random Keys: "+ generatedNumbers);
        return ranNum;
    }

    public static void clearSet(){
        generatedNumbers.clear();
    }


}
