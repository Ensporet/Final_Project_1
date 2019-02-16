package com.Lab1.My_Sort;

public interface ISortBubble extends  ISort{


    public void sort(int [] integerArray , boolean isDrown);

    @Override
    default void sort(int[] ... integerArray){

        for (int [] i : integerArray) {
            sort(i, false);
        }
    };
}
