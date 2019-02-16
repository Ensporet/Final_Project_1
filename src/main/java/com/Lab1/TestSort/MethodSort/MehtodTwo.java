package com.Lab1.TestSort.MethodSort;

import java.util.Random;

public class MehtodTwo implements IMethodFill {



    @Override
    public void methodFill(int[]... integers) {

        int i = -1;
        Random random = new Random();
        for (int[] ints : integers) {
            for (int number = 0 ; number < ints.length; number++) {
                ints[number] = ++i;
            }
            ints[ints.length - 1] = random.nextInt();
            i--;
        }

    }
}
