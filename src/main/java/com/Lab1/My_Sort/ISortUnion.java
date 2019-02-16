package com.Lab1.My_Sort;

public interface ISortUnion  extends ISort{


    @Override
   default void sort(int[]... integerArray) {

        sort(null , integerArray) ;

    };

    int[] sort(ISort sort , int[] ...integerArray);
}
