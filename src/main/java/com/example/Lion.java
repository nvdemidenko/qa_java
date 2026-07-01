package com.example;

import java.util.List;

public class Lion {

    private boolean hasMane;
    private Feline feline;

    public Lion(Feline feline, String sex) throws Exception {
        if (feline == null) {
            throw new IllegalArgumentException("Feline is required");
        }
        this.feline = feline;
        if ("Самец".equals(sex)) {
            hasMane = true;
        } else if ("Самка".equals(sex)) {
            hasMane = false;
        } else {
            throw new Exception("используйте допустимые значения пола животного - самец или самка");
        }
    }

    public int getKittens() {
        return feline.getKittens();
    }

    public boolean doesHaveMane() {
        return hasMane;
    }

    public List<String> getFood() throws Exception {
        return feline.getFood("Хищник");
    }
}

