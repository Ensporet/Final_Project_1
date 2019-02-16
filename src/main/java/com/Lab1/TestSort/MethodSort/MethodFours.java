package com.Lab1.TestSort.MethodSort;

import java.util.Random;

public class MethodFours implements IMethodFill {

    @Override
    public void methodFill(int[]... integers) {

        Random random = new Random();
        for (int[] ints : integers) {
            for (int number = 0 ; number < ints.length; number++) {
                ints[number] = random.nextInt();
            }
        }

    }
}
