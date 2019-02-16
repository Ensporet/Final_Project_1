package com.Lab1.TestSort.MethodSort;

public class MethodOne implements IMethodFill {

    @Override
    public void methodFill(int[]... integers) {

        int i = -1;
        for (int[] ints : integers) {
            for (int number = 0 ; number < ints.length; number++) {
                ints[number] = ++i;
            }
        }

    }
}
