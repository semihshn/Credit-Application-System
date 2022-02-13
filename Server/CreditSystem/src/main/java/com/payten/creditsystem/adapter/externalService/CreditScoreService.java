package com.payten.creditsystem.adapter.externalService;

import java.util.Random;

public class CreditScoreService {

    public static Integer retrieveCreditScore(String identificationNumber){
        Random rand = new Random(); //instance of random class
        int upperbound = 1900;
        //generate random values from 0-24
        int int_random = rand.nextInt(upperbound);

        return int_random;
    }
}
