package com.example.ap_final;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Testclass {

    @Test
    public void test1(){
        main_Controller c = new main_Controller();
        initial_Controller i  = new initial_Controller();

        assertEquals(c.score_data  , i.score_data);
    }

    @Test
    public void test2(){

        initial_Controller i = new initial_Controller();

        assertTrue(i.skinHandler.getHollow() == 0 &&  i.skinHandler.getHornet() == 0 && i.skinHandler.getShadow() == 0);
    }

    @Test
    public void test3(){
        main_Controller c = new main_Controller();
        initial_Controller i  = new initial_Controller();

        assertEquals(c.skinHandler  , i.skinHandler);
    }


}
