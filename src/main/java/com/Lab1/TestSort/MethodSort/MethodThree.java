package com.Lab1.TestSort.MethodSort;

public class MethodThree implements IMethodFill {


    @Override
    public void methodFill(int[]... integers) {

        int i = 0;

        for (int [] ints : integers) {
            i += ints.length;
        }


        for (int[] ints : integers) {
            for (int number = 0 ; number < ints.length; number++) {
                ints[number] = --i;
            }
        }

    }
}
