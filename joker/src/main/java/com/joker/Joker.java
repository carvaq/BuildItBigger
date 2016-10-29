package com.joker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Caro Vaquero
 * Date: 29.10.2016
 * Project: FinalProject
 */

public class Joker {
    private List<String> jokes;

    public Joker() {
        jokes = new ArrayList<>(4);
        jokes.add("Yo momma is so fat, she has multidex enabled.");
        jokes.add("When your hammer is C++, everything looks like a thumb.");
        jokes.add("What is the objected-oriented way to become wealthy? Inheritance!");
        jokes.add("!false, it's funny because it's true.");
    }

    public String getJoke() {
        int index = ThreadLocalRandom.current().nextInt(0, 5);
        return jokes.get(index);
    }
}
