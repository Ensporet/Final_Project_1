package com.Lab1;

import com.Ens_Library.File.Excel.Excel;
import com.Lab1.ConsoleOutput.IConsoleOutput;
import com.Lab1.My_Sort.ISort;
import com.Lab1.My_Sort.*;
import com.Lab1.TestSort.CreateArray.EmptyArrayArPr;
import com.Lab1.TestSort.CreateArray.IEmptyCreateArray;
import com.Lab1.TestSort.MethodSort.IMethodFill;
import com.Lab1.TestSort.ObjectTestSort.IObjectTestSort;
import com.Lab1.TestSort.TestSort;
import com.Lab1.TestSort.MethodSort.*;
import com.Lab1.ToExcel.SaveTestData;

import java.io.File;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;


public class Main {

    /**
     * path for catalog save excel file
     */
    public final static File PATH_OF_SAVE =
            // new File("SaveTestCatalog");
            new File("C:\\Users\\enspo\\Desktop\\Новая папка");


    public final static long VERSION = 3L;

    /**
     * Number of iterations perform
     */
    public final static int ITERATION = 10;


    public static void main(String[] args) {

        IObjectTestSort[][] iObjectTestSorts = new IObjectTestSort[Main.ITERATION][]; // result of test

        ISort[] sorts = {                                                             // sort class
                new SortBubbleMaxMin(),
                new SortBubbleMinMax(),
                new SortArrays(),
                new SortExchange(),
                new SortRecursion(),
                new SortUnion()
        };

        Arrays.sort(sorts, (o1, o2) -> {                                               // sor , sor by name

            String name1 = (o1 == null) ? null : o1.getName(),
                    name2 = (o2 == null) ? null : o2.getName();
            int size1 = (name1 == null) ? 0 : name1.length(),
                    size2 = (name2 == null) ? 0 : name2.length();

            for (int i = 0; i < size1 && i < size2; i++) {
                if (name1.charAt(i) > name2.charAt(i)) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return 0;
        });

        IMethodFill[] methodFills = {                                                   // methods fill empty array
                new MethodOne(),
                new MethodTwo(),
                new MethodThree(),
                new MethodFours()
        };


        IEmptyCreateArray iEmptyCreateArray = new EmptyArrayArPr(100, 100); // creator empty array
        for (int i = 0; i < iObjectTestSorts.length; i++) {
            iObjectTestSorts[i] = new TestSort().test(                       // create a return of test
                    sorts,
                    methodFills,
                    iEmptyCreateArray

            );


        }

        Excel excel = new Excel(
                new File(
                        Main.PATH_OF_SAVE +
                                File.separator +
                                "v" + Main.VERSION +
                                "Date" +
                                DateFormat.getDateInstance(
                                        DateFormat.DEFAULT).format(Calendar.getInstance().getTime()
                                ) +
                                ".xlsx")
        );
        excel.getSheet("All charts");

        new SaveTestData().save(
                excel
                , iObjectTestSorts);


        for (IObjectTestSort[] obj : iObjectTestSorts) {                // print result of test to console
            System.out.println("//:" + obj[0].getLengthArray());
            IConsoleOutput.printlnClass(obj);

        }
    }
}
