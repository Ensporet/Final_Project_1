package com.Lab1.TestSort.MethodSort;

/**
 * 1 2 3 ... to max
 */
public class MethodOne implements IMethodFill {

    @Override
    public void methodFill(int[]... integers) {

        int i = -1;
        for (int[] ints : integers) {
            for (int number = 0; number < ints.length; number++) {
                ints[number] = ++i;
            }
        }

    }

    @Override
    public String getMethodName() {
        return "Ascending numbers (1,2,3 ... max)";
    }
}
