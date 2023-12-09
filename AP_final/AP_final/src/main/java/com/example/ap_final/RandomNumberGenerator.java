package com.example.ap_final;

public class RandomNumberGenerator {

    private static RandomNumberGenerator rng;

    private RandomNumberGenerator(){};

    public static RandomNumberGenerator getRng(){
        if(rng == null) return new RandomNumberGenerator();
        else return rng;
    }

    int get_random(int min , int max){
        return (int)(Math.random() * max) + min;
    }

}
