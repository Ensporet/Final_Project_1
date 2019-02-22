package com.Lab1.ToExcel;

import com.Ens_Library.File.Excel.Excel;
import com.Lab1.TestSort.ObjectTestSort.IObjectTestSort;

/**
 * The interface is designed to implement saving test results .
 */
public interface ISaveTestData {


    void save(Excel excel, IObjectTestSort[]... objectTestSort);


}
