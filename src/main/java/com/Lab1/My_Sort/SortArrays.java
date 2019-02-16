package com.Lab1.My_Sort;


import java.util.Arrays;

public class SortArrays implements ISort {

    @Override
    public void sort(int[] ... integerArray) {

        if (integerArray == null) {
            return;
        }
        for (int[] i : integerArray) {
            Arrays.sort(i);
        }
    }
}
