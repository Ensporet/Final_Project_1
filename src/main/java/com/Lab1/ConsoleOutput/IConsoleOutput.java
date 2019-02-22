package com.Lab1.ConsoleOutput;

import com.Lab1.TestSort.ObjectTestSort.IObjectTestSort;
import org.jetbrains.annotations.NotNull;

/**
 * Static class for data output to the console
 */
public interface IConsoleOutput {

    static void printlnClass(@org.jetbrains.annotations.NotNull IObjectTestSort iObjectTestSort) {

        System.out.println(iObjectTestSort.toString());
    }

    static void printlnClass(@NotNull IObjectTestSort[] iObjectTestSort) {


        for (IObjectTestSort objectTestSort : iObjectTestSort) {
            printlnClass(objectTestSort);
        }
    }

    static void printlnLengthArrses(int[]... ints) {

        if (ints == null) {
            System.out.println("Arrases is null");
        } else {
            String s = "";
            for (int i[] : ints) {
                s += "[length : " + ((i == null) ? "null" : i.length) + "]";
            }

            System.out.println(s);
        }
    }

    static void printlnArray(@NotNull int[]... integerArraies) {

        for (int[] integerArray : integerArraies) {

            printlnArray(integerArray);
        }

    }

    static void printlnArray(int[] integerArray) {

        if (integerArray == null || integerArray.length == 0) {
            System.out.println("Array is empty");
        } else {
            for (int i : integerArray) {
                System.out.print("[" + i + "]");
            }
            System.out.println();
        }


    }


}
