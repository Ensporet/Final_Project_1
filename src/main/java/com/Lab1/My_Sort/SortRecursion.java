package com.Lab1.My_Sort;

public class SortRecursion implements ISort {

    @Override
    public void sort(int[]... integerArrases) {

        if (integerArrases == null) {
            return;
        }

        for (int[] array : integerArrases) {
            if (array != null) {


                sort(array, 0, array.length - 1);
            }
        }
    }

    @Override
    public String getName() {
        return "Recursive sorting with half division";
    }

    void sort(int in[], int a, int b) {
        int i, j;
        double sr = 0;
        if (a >= b) {
            return;
        }
        for (i = a; i <= b; i++) {
            sr += in[i];
        }
        sr = sr / (b - a + 1);
        for (i = a, j = b; i <= j; ) {
            if (in[i] < sr) {
                i++;
                continue;
            }
            if (in[j] >= sr) {
                j--;
                continue;
            }
            int c = in[i];
            in[i] = in[j];
            in[j] = c;
            i++;
            j--;
        }
        if (i == a) {
            return;
        }

        sort(in, a, j);
        sort(in, i, b);
    }


}
