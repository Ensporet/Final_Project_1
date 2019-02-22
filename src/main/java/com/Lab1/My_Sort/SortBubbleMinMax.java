package com.Lab1.My_Sort;

/**
 * Bubble sorting with building from minimum to maximum
 */
public class SortBubbleMinMax implements ISort {

    @Override
    public void sort(int[]... ints) {

        if (ints == null) {
            return;
        }

        for (int[] integerArray : ints) {

            if (integerArray == null) {
                return;
            }


            for (int iterations = (integerArray.length - 1); iterations > 0; iterations--) {
                sortIterateMinMax(integerArray, iterations);
            }


        }
    }

    @Override
    public String getName() {
        return "Bubble sorting, from minimum to maximum";
    }

    private void sortIterateMinMax(int[] integerArray, final int indexFinalist) {

        for (int index = 0; index < indexFinalist; index++) {

            min_max(integerArray, index, (index + 1));

        }
    }

    private void min_max(int[] integerArray, int indexOne, int indexTwo) {

        if (integerArray[indexOne] > integerArray[indexTwo]) {

            int i = integerArray[indexTwo];
            integerArray[indexTwo] = integerArray[indexOne];
            integerArray[indexOne] = i;
        }
    }
}
