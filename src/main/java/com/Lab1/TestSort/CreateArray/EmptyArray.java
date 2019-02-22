package com.Lab1.TestSort.CreateArray;

import java.util.Random;

public class EmptyArray implements IEmptyCreateArray {

    @Override
    public int[] createArray(int length) {

        if (length < 0) {
            length = new Random().nextInt(correctMAX_ELEMENT());
        }

        if (length > IEmptyCreateArray.MAX_ELEMENT_OF_ARRASES) {
            length = IEmptyCreateArray.MAX_ELEMENT_OF_ARRASES;
        }

        return new int[(length < IEmptyCreateArray.MIN_ELEMENTS_OF_ARRAY) ?
                IEmptyCreateArray.MIN_ELEMENTS_OF_ARRAY : length];
    }

    @Override
    public int[][] createArrases(int sizeArrases, int sizeAllElement) {

        Random random = new Random();

        if (sizeArrases < 0) {
            sizeArrases = random.nextInt(IEmptyCreateArray.MAX_ARRASES);
        }
        if (sizeArrases == 0) {
            sizeArrases = 1;
        }
        int[][] ret = new int[sizeArrases][];

        if (sizeAllElement < 0) {
            int maxElement = correctMAX_ELEMENT() - (sizeArrases * IEmptyCreateArray.MIN_ELEMENTS_OF_ARRAY);
            for (int i = 0; i < sizeArrases; i++) {

                int ran = random.nextInt(maxElement) / sizeArrases;
                if (ran < IEmptyCreateArray.MIN_ELEMENTS_OF_ARRAY) {
                    ran = IEmptyCreateArray.MIN_ELEMENTS_OF_ARRAY;
                } else {
                    maxElement -= ran;
                }

                ret[i] = new int[ran];
            }
        } else {

            {
                int number;
                if ((number = (sizeArrases * IEmptyCreateArray.MIN_ELEMENTS_OF_ARRAY)) > sizeAllElement) {
                    sizeAllElement = number;
                } else if ((number = correctMAX_ELEMENT()) < sizeAllElement) {
                    sizeAllElement = number;
                }
            }

            int last = sizeAllElement;
            for (int i = 0; i < sizeArrases; i++) {

                int size;
                if (i == (sizeArrases - 1)) {
                    size = last;
                } else {
                    size = sizeAllElement / sizeArrases;
                    if (size == 0) {
                        size = IEmptyCreateArray.MIN_ELEMENTS_OF_ARRAY;
                    }
                    last -= size;
                }

                ret[i] = new int[size];
            }
        }
        return ret;
    }
}
