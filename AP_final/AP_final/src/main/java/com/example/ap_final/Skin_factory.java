package com.example.ap_final;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class Skin_factory {

    public Image get_skin(String img) throws FileNotFoundException {
        if(Objects.equals(img, "hollow")) return new Image(new FileInputStream("hollow_knight.png"));
        if(Objects.equals(img, "hornet")) return new Image(new FileInputStream("hornet.png"));
        if(Objects.equals(img, "shadow")) return new Image(new FileInputStream("shadow.png"));
        return new Image(new FileInputStream("sprite.png"));

    }
}
