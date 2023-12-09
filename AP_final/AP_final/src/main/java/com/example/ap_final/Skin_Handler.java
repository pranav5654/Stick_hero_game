package com.example.ap_final;

import javafx.scene.control.Skin;

import java.util.Objects;

public class Skin_Handler {


    private int shadow =0 ;
    private int hollow =0 ;
    private int hornet= 0 ;

    private String current = "";


    private Skin_Handler(){};

    private static Skin_Handler s;


    static public Skin_Handler get_skin(){
        if(s == null) s = new Skin_Handler();
        return s;
    }

    public int getShadow() {
        return shadow;
    }

    public void setShadow(int shadow) {
        this.shadow = shadow;
    }

    public int getHollow() {
        return hollow;
    }

    public void setHollow(int hollow) {
        this.hollow = hollow;
    }

    public int getHornet() {
        return hornet;
    }

    public void setHornet(int knight) {
        this.hornet = knight;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }
}
