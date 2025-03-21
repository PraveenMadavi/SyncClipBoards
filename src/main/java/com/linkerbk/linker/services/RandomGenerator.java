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
        int number;
        do {
            number = 100_000 + random.nextInt(900_000); // Generates a number between 100000 - 999999
        } while (!generatedNumbers.add(number)); // Ensure uniqueness
        System.out.println("Random nums: "+ generatedNumbers);
        return number;
    }

    public static void clearSet(){
        generatedNumbers.clear();
    }


}
