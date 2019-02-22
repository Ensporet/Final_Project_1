package com.Ens_Library.File.Excel;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public interface IExcel {


    Sheet[] getAllSheet();

    Sheet getSheet(String nameSheet);

    Sheet getSheet();

    Row getRow(String nameSheet, int indexRow);

    Row getRow(int indexRow);

    Row getRow();

    Cell getCell(String nameSheet, int indexRow, int indexColum);

    Cell getCell(int indexColum);

    Cell getCell();


}
