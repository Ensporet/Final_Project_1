package com.Lab1.My_Sort;

/**
 * Sort with union
 */
public interface ISortUnion extends ISort {


    @Override
    default void sort(int[]... integerArray) {

        sort(null, integerArray);

    }

    /**
     * Sorts and combines all in one array
     *
     * @param sort
     * @param integerArray
     * @return
     */
    int[] sort(ISort sort, int[]... integerArray);
}
