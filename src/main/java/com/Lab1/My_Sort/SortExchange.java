package com.Lab1.My_Sort;

public class SortExchange implements ISort {

    @Override
    public void sort(int[]... integerArray) {

        if (integerArray == null) {
            return;
        }

        for (int[] i : integerArray) {

            if (i != null) {

                for (int integer = 0; integer < i.length; integer++) {

                    int min = integer;
                    for (int searthMin = integer + 1; searthMin < i.length; searthMin++) {

                        if (i[min] > i[searthMin]) {
                            min = searthMin;
                        }
                    }

                    if (min != integer) {

                        int chen = i[integer];
                        i[integer] = i[min];
                        i[min] = chen;
                    }
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Exchange sorting";
    }


}
