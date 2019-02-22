/**
 * @(#)com.Ens_Library.File.Excel.ElementСonstructors.Table.ExcelTable 1.00 21.02.2019
 * Copyright :  Yevhenii Babiak
 * <p>
 * This software is intended for the final course of Go IT Java # 12 Core.
 * Use for commercial purposes is prohibited.
 */
package com.Ens_Library.File.Excel.ElementСonstructors.Table;


import org.apache.poi.ss.usermodel.*;


import java.util.Calendar;
import java.util.Date;

/**
 * Designed to create excel table in excel sheet
 *
 * @author Yevhenii Babiak
 * @version 1 21.02.2019
 */
public class ExcelTable {


    /**
     * Defines the maximum long array.
     *
     * @param objects any variation
     * @return int 0 ... Integer.MAX_VALUE
     */
    protected int maxLength(Object[][] objects) {

        int max = 0;

        if (objects != null) {
            for (Object obj[] : objects) {
                int len = (obj == null) ? 0 : obj.length;
                if (len > max) {
                    max = len;
                }
            }
        }
        return max;
    }

    //..................................................................................................................


    /**
     * create and added the table to excel sheet
     *
     * @param sheet       not null
     * @param indexRow    if negative that value = 0
     * @param indexColum  if negative that value = 0
     * @param nameOfRow   used @param values [0] for name column
     * @param nameofColum used @param values [n][0] for name row
     * @param values      object for write in table. One array Object[] = one row
     */
    public void addTable(Sheet sheet, int indexRow, int indexColum, boolean nameOfRow, boolean nameofColum,
                         Object[]... values) {
        addTable(sheet, indexRow, indexColum, nameOfRow, nameofColum, maxLength(values), values);
    }

    /**
     * create and added the table to excel sheet
     *
     * @param sheet       not null
     * @param indexRow    if negative that value = 0
     * @param indexColum  if negative that value = 0
     * @param nameOfRow   used @param values [0] for name column
     * @param nameofColum used @param values [n][0] for name row
     * @param maxLength   max length cell in row
     * @param values      object for write in table. One array Object[] = one row
     */
    public void addTable(Sheet sheet, int indexRow, int indexColum, boolean nameOfRow, boolean nameofColum,
                         int maxLength, Object[]... values) {


        if (values == null || values.length < 1 || sheet == null) {
            return;
        }

        if (indexColum < 0) {
            indexColum = 0;
        }

        if (indexRow < 0) {
            indexRow = 0;
        }

        int index = 0;

        if (nameofColum) {

            addRow(
                    sheet,
                    indexRow++,
                    indexColum,
                    false,
                    false,
                    null,
                    cellStyleName(sheet.getWorkbook()),
                    maxLength,
                    values[index++]
            );
        }

        for (; index < values.length; index++) {

            addRow(
                    sheet,
                    indexRow++,
                    indexColum,
                    nameOfRow,
                    (index == values.length - 1),
                    cellStyleName(sheet.getWorkbook()),
                    cellStyle(sheet.getWorkbook()),
                    maxLength,
                    values[index]
            );
        }
    }

    //..................................................................................................................

    /**
     * create and added the row for  table to excel sheet
     *
     * @param sheet      not null
     * @param indexRow   if negative that value = 0
     * @param indexColum if negative that value = 0
     * @param maxLength  max length cell in row
     * @param values     object for write in table. One array Object[] = one row
     */
    public void addRow(Sheet sheet, int indexRow, int indexColum, boolean nameRow, boolean isLastRow,
                       CellStyle cellStyleName, CellStyle cellStyle0, int maxLength, Object... values) {

        if (sheet == null) {
            return;
        }

        if (indexRow < 0) {
            indexRow = 0;
        }

        if (indexColum < 0) {
            indexColum = 0;
        }

        Row row;
        if ((row = sheet.getRow(indexRow)) == null) {
            row = sheet.createRow(indexRow);
        }

        Cell cell;
        CellStyle cellStyle;

        if (values != null) {

            for (int i = 0; i < maxLength; i++) {

                Object obj = (i < values.length) ? values[i] : null;
                cell = row.createCell(indexColum);
                cellStyle = sheet.getWorkbook().createCellStyle();


                if (i == 0 && nameRow) {
                    if (cellStyleName != null) {
                        cellStyle.cloneStyleFrom(cellStyleName);
                    }
                } else if (cellStyle0 != null) {
                    cellStyle.cloneStyleFrom(cellStyle0);
                }

                if (isLastRow) {
                    cellStyleLastBottom(cellStyle);
                }

                if (i == maxLength - 1) {
                    cellStyleLastRight(cellStyle);
                }

                cell.setCellStyle(cellStyle);

                if (obj != null) {
                    if (obj instanceof Number) {
                        cell.setCellValue(((Number) obj).doubleValue());
                    } else if (obj instanceof Boolean) {
                        cell.setCellValue((Boolean) obj);
                    } else if (obj instanceof Calendar) {
                        cell.setCellValue((Calendar) obj);
                    } else if (obj instanceof Date) {
                        cell.setCellValue((Date) obj);
                    } else if (obj instanceof Character) {
                        cell.setCellValue(((Character) obj).toString());
                    } else {
                        cell.setCellValue(obj.toString());
                    }
                }
                sheet.autoSizeColumn(indexColum++);
            }
        }
    }

    //..........................................Style...................................................................

    public CellStyle cellStyle(Workbook workbook) {

        CellStyle cellStyle = workbook.createCellStyle();
        //BorderStyle borderStyle = BorderStyle.HAIR;

        cellStyle.setBorderBottom(BorderStyle.HAIR);
        cellStyle.setBorderRight(BorderStyle.HAIR);
        cellStyle.setBorderLeft(BorderStyle.HAIR);
        cellStyle.setBorderTop(BorderStyle.HAIR);

        return cellStyle;
    }


    public void cellStyleLastRight(CellStyle cellStyle) {


        cellStyle.setBorderRight(BorderStyle.MEDIUM);

    }

    public void cellStyleLastBottom(CellStyle cellStyle) {

        cellStyle.setBorderBottom(BorderStyle.MEDIUM);

    }

    public CellStyle cellStyleName(Workbook workbook) {

        CellStyle cellStyle = workbook.createCellStyle();
        BorderStyle borderStyle = BorderStyle.MEDIUM;


        Font font = workbook.createFont();
        font.setBold(true);
        cellStyle.setFont(font);

        cellStyle.setBorderBottom(borderStyle);
        cellStyle.setBorderRight(borderStyle);
        cellStyle.setBorderLeft(borderStyle);
        cellStyle.setBorderTop(borderStyle);

        return cellStyle;
    }
    //..................................................................................................................

    private void addvValueToRow(Row row, int indexColum, Object... objects) {

        for (Object obj : objects) {
            row.createCell(indexColum++).setCellValue(obj.toString());
        }
    }

    private void addvValueToRow(Row row, int indexColum, Date... dates) {

        for (Date d : dates) {
            row.createCell(indexColum++).setCellValue(d);
        }
    }

    private void addvValueToRow(Row row, int indexColum, boolean... booleans) {

        for (boolean b : booleans) {
            row.createCell(indexColum++).setCellValue(b);
        }
    }

    private void addvValueToRow(Row row, int indexColum, Calendar... calendars) {

        for (Calendar c : calendars) {
            row.createCell(indexColum++).setCellValue(c);
        }

    }

    private void addvValueToRow(Row row, int indexColum, double... numbers) {

        for (double d : numbers) {
            row.createCell(indexColum++).setCellValue(d);
        }

    }
    //..................................................................................................................


}
