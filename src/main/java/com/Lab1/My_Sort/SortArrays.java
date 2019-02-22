package com.Lab1.My_Sort;


import java.util.Arrays;

/**
 * Sort using standard libraries
 */
public class SortArrays implements ISort {

    @Override
    public void sort(int[]... integerArray) {

        if (integerArray == null) {
            return;
        }
        for (int[] i : integerArray) {
            Arrays.sort(i);
        }
    }

    @Override
    public String getName() {
        return "Sort by method Arrays.sort";
    }
}
