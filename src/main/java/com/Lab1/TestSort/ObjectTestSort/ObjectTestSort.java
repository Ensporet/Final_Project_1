package com.Lab1.TestSort.ObjectTestSort;

import java.util.HashMap;

public class ObjectTestSort implements IObjectTestSort {

    private final HashMap<String, Long> TIME = new HashMap<>();
    private final String NAME;
    private final int LENGTH_ARRAY;

    public ObjectTestSort(String name, int length_array) {
        this.NAME = name;
        LENGTH_ARRAY = length_array;
    }


    @Override
    public HashMap<String, Long> getTime() {
        return this.TIME;
    }

    @Override
    public String getName() {
        return this.NAME;
    }

    @Override
    public int getLengthArray() {
        return LENGTH_ARRAY;
    }


    @Override
    public String toString() {
        return "Name : " + getName() + " time millisecond : " + getTime() + " length array : " + getLengthArray();
    }
}
