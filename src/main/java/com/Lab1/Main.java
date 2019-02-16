package com.Lab1;


import com.Ens_Library.File.Excel.Excel;
import com.Ens_Library.File.File_default;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        /**
         * Array
          */
/*

        IObjectTestSort.printlnClass( new TestSort().test(new ISort[]{new SortBubble(), new SortArrays(), new SortExchange(), new SortUnion()},
                new IMethodFill[]{new MethodOne(), new MehtodTwo(), new MethodThree(), new MethodFours()},
                new IEmptyCreateArray() {
                }));

      */


        //new XSSFWorkbook();
        //new XSSFSheet().;
        //new XSSFRow();
        //new XSSFCell();

        //new HSSFWorkbook();
        //new HSSFSheet().;
        String s = "C:\\Users\\enspo\\Desktop\\x.xlsx";
        File f = new File(s);
        Excel excel = new Excel(f);

        excel.writeAndClose();


    }

}
