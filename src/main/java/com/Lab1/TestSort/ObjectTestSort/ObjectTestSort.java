package com.Lab1.TestSort.ObjectTestSort;

import java.util.HashMap;

public class ObjectTestSort implements IObjectTestSort {

    private final HashMap<String , Long> time = new HashMap<>();
    private final String name;

    public ObjectTestSort(String name) {

        this.name = name;
    }


    @Override
    public HashMap<String , Long> getTime() {
        return this.time;
    }

    @Override
    public String getName() {
        return this.name;
    }


    @Override
    public String toString() {
        return "Name : " + getName() + " time millisecond : " + getTime();
    }
}
