/**
 * @(#)com.Ens_Library.File.Excel.ElementСonstructors.Chart.ExcelChartLine 1.00 21.02.2019
 * Copyright :  Yevhenii Babiak
 * <p>
 * This software is intended for the final course of Go IT Java # 12 Core.
 * Use for commercial purposes is prohibited.
 */


package com.Ens_Library.File.Excel.ElementСonstructors.Chart;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.*;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;

import java.lang.reflect.Array;


/**
 * Designed to create graphics Excel
 *
 * @author Yevhenii Babiak
 * @version 1 21.02.2019
 */
public class ExcelChartLine {

    /**
     * Data and graph are located on the same page. The data range starts from 0 and 0
     *
     * @param sheetData Excel page where data is located !not null
     * @param nameX     Name for axis X
     * @param nameY     Name for axis Y
     */
    public void addToSheet(Sheet sheetData, String nameX, String nameY, String nameChart) {
        addToSheet(sheetData, -1, -1, -1, -1,
                null, -1, -1, -1, -1,
                nameX, nameY, nameChart);
    }

    /**
     * Data and graph are located on the same page. The data range starts from 0 and 0
     *
     * @param sheetData Excel page where data is located !not null
     */
    public void addToSheet(Sheet sheetData) {
        addToSheet(sheetData, -1, -1, -1, -1,
                null, -1, -1, -1, -1,
                null, null, null);
    }

    /**
     * @param sheetData         Excel page where data is located !not null
     * @param firstRow          Coordinate row excel of located is data for chart. If negative number that used
     *                          0 coordinate
     * @param firstColum        Coordinate column excel of located is data for chart. If negative number that used
     *                          0 coordinate
     * @param sizeColum         Data range column length. If negative then it takes all adjacent cells
     *                          [1][2][3][...][not null]
     * @param sizeRow           Data range row length. If negative then it takes all adjacent cells
     *                          [1][2][3][...] [not null]
     * @param sheetChart        Excel page where chart is located. If null, method used a @param sheetData
     * @param replaceChartRow   Location a chart in excel page bu value row. If negative that used a @param firstRow
     *                          and plus one number
     * @param replaceChartColum Location a chart in excel page bu value column If negative that used a @param firstColum
     *                          and plus one number
     * @param lengthX           Length chart. if negative that used a size data @param sizeColum
     * @param lengthY           Height chart. if negative that used a size data @param sizeRow
     * @param nameX             Name for axis X
     * @param nameY             Name for axis Y
     */
    public void addToSheet(Sheet sheetData,
                           int firstRow, int firstColum, int sizeColum, int sizeRow,
                           Sheet sheetChart,
                           int replaceChartRow, int replaceChartColum, int lengthX, int lengthY,
                           String nameX, String nameY, String nameChart) {

        // Normalise param
        if (firstRow < 0) {
            firstRow = 0;
        }

        if (firstColum < 0) {
            firstColum = 0;
        }

        if (sizeColum < 0) {
            sizeColum = howManyCellsInRow(sheetData.getRow(firstRow), firstColum);
        }

        if (sizeRow < 0) {
            sizeRow = howManyRowInColum(sheetData, firstRow, firstColum);
        }

        if (replaceChartRow < 0) {
            replaceChartRow = (sheetChart == null) ? (firstRow + sizeRow) : 0;
        }

        if (replaceChartColum < 0) {
            replaceChartColum = (sheetChart == null) ? firstColum : 0;
        }

        if (sheetChart == null) {
            sheetChart = sheetData;
        }

        if (lengthX < 1) {
            lengthX = sizeColum;
        }

        if (lengthY < 1) {
            lengthY = sizeRow;
        }


        XSSFChart chart = createChart(sheetChart, replaceChartRow, replaceChartColum, lengthY, lengthX);
        if (nameChart != null) {
            chart.setTitleText(nameChart);
        }

        XDDFNumericalDataSource[] dataSource = dataSourceDoubles(sheetData, firstRow, 1, sizeColum, sizeRow);
        XDDFChartData data = chartData(chart, nameX, nameY);

        XDDFDataSource xs = dataSource[0];                                                  //Axis X

        PresetColor[] presetColors = getValuesFromArrayIteration(30, dataSource.length - 1,
                PresetColor.values(), new int[]{0, 1, 2, 3});
        for (int i = 1; i < dataSource.length; i++) {                                       //Fill data, create lines
            XDDFLineChartData.Series series = (XDDFLineChartData.Series) data.addSeries(xs,
                    dataSource[i]);
            {
                Row row = sheetData.getRow(firstRow + i);
                Cell cell;
                String s;
                series.setTitle(
                        ((row == null ||
                                ((cell = row.getCell(firstColum)) == null) ||
                                ((s = cell.getStringCellValue()) == null))
                                ? ("N:" + i) : s), null);

            }

            series.setSmooth(true);
            series.setMarkerStyle(MarkerStyle.DOT);
            series.setMarkerSize((short) 6);
            solidLineSeries(data, i - 1, presetColors[i - 1]);
        }

        chart.plot(data);

    }


    //------------------------------------------------------------------------------------------------------------------

    protected <T> T[] getValuesFromArrayIteration(int startIndex, int sizeIteration, T[] arrayValues) {
        return getValuesFromArrayIteration(startIndex, sizeIteration, arrayValues, null);
    }

    /**
     * Creates an array of links to the entire call iteration.
     *
     * @param startIndex        any number
     * @param sizeIteration
     * @param arrayValues
     * @param indexArrayNotUsed
     * @param <T>
     * @return T[] or null sizeIteration < 0 , arrayValues ==  null , indexArrayNotUsed numbers index all array
     */
    protected <T> T[] getValuesFromArrayIteration(int startIndex, int sizeIteration, T[] arrayValues,
                                                  int[] indexArrayNotUsed) {

        if (sizeIteration < 0 || arrayValues == null) {
            return null;
        }


        T[] values = (T[]) Array.newInstance(arrayValues.getClass().getComponentType(), sizeIteration);

        startIndex -= 1;

        for (int i = 0; i < sizeIteration; i++) {
            startIndex = getIndexFromArray(++startIndex, arrayValues.length, indexArrayNotUsed);

            if (startIndex < 0) {
                return null;
            }
            values[i] = arrayValues[startIndex];
        }

        return values;
    }


    protected <T> T getValueFromArray(int index, T[] arrayValues) {
        return getValueFromArray(index, arrayValues, null);
    }

    protected <T> T getValueFromArray(int index, T[] arrayValues, int[] indexsNotUsed) {

        if (arrayValues == null) {
            return null;
        }

        index = getIndexFromArray(index, ((arrayValues == null) ? 0 : arrayValues.length), indexsNotUsed);

        return (index == -1) ? null : arrayValues[index];
    }


    protected int getIndexFromArray(int index, int lengthArray) {
        return getIndexFromArray(index, lengthArray, 0, null);
    }

    protected int getIndexFromArray(int index, int lengthArray, int[] indexNotUsed) {
        return getIndexFromArray(index, lengthArray, 0, indexNotUsed);
    }

    protected int getIndexFromArray(int index, int lengthArray, int iterationNumber, int[] indexNotUsed) {

        if (lengthArray < 1 || iterationNumber == lengthArray) {
            return -1;
        }

        while (index < 0) {
            index += lengthArray;
        }
        while (index >= lengthArray) {
            index -= lengthArray;
        }

        if (indexNotUsed != null) {
            for (int i : indexNotUsed) {
                if (index == i) {
                    return getIndexFromArray(++index, lengthArray, ++iterationNumber, indexNotUsed);
                }
            }
        }

        return index;
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * Create of line
     *
     * @param data  XDDFChartData. Mast not have null
     * @param index pos in a chart . Mast not have negative value
     * @param color PresetColor. Mast not have null
     */
    private void solidLineSeries(XDDFChartData data, int index, PresetColor color) {
        XDDFSolidFillProperties fill = new XDDFSolidFillProperties(XDDFColor.from(color));
        XDDFLineProperties line = new XDDFLineProperties();
        line.setFillProperties(fill);

        XDDFChartData.Series series = data.getSeries().get(index);
        series.setShowLeaderLines(true);
        XDDFShapeProperties properties = series.getShapeProperties();

        if (properties == null) {
            properties = new XDDFShapeProperties();
        }
        properties.setLineProperties(line);
        series.setShapeProperties(properties);
    }

    /**
     * @param chart drawing. Mast not have null
     * @param nameX name axis X. If null - not added name
     * @param nameY name axis Y. If null - not added name
     * @return XDDFLineChartData
     */
    private XDDFLineChartData chartData(XDDFChart chart, String nameX, String nameY) {

        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        if (nameX != null) {
            bottomAxis.setTitle(nameX);
        }
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        if (nameY != null) {
            leftAxis.setTitle(nameY);
        }
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        XDDFLineChartData chartData = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);

        return chartData;
    }

    /**
     * Create data for line of chart
     *
     * @param sheet    excel list. Mast not have null
     * @param row      index of row data. Mast have not negative value
     * @param firstCol diapason of data. First element. Mast have not negative value
     * @param sizeCol  diapason of data. Size element . Mast have not negative value
     * @param sizeRow  size all row date. Mast have not negative value
     * @return XDDFNumericalDataSource[]
     */
    private XDDFNumericalDataSource[] dataSourceDoubles(Sheet sheet, int row, int firstCol, int sizeCol, int sizeRow) {

        XDDFNumericalDataSource[] doubleXDDFDataSource = new XDDFNumericalDataSource[sizeRow];

        for (int i = 0; i < sizeRow; i++) {
            doubleXDDFDataSource[i] = dataSourceDouble(sheet, row++, firstCol, sizeCol - 1);
        }

        return doubleXDDFDataSource;
    }

    /**
     * Create data for line of chart
     *
     * @param sheet    excel list. Mast have not negative value
     * @param row      index of row data. Mast have not negative value
     * @param firstCol diapason of data. First element. Mast have not negative value
     * @param sizeCol  diapason of data. Size element. Mast have not negative value
     * @return XDDFNumericalDataSource
     */
    private XDDFNumericalDataSource<Double> dataSourceDouble(Sheet sheet, int row, int firstCol, int sizeCol) {

        return XDDFDataSourcesFactory.fromNumericCellRange((XSSFSheet) sheet,
                new CellRangeAddress(row, row, firstCol, firstCol + sizeCol - 1));
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * drawing chart in location x y and length ..
     *
     * @param sheet        excel sheet. mast have not null
     * @param replaceRow   location number y. Mast have not negative value
     * @param replaceColum location number x. Mast have not negative value
     * @param lengthRow    mast have not negative value
     * @param lengthColum  mast have not negative value
     * @return XSSFChart
     */
    private XSSFChart createChart(Sheet sheet, int replaceRow, int replaceColum, int lengthRow, int lengthColum) {
        XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, replaceColum, replaceRow,
                replaceColum + lengthColum + 1, replaceRow + lengthRow - 1);

        XSSFChart chart = drawing.createChart(anchor);
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.TOP_RIGHT);
        return chart;
    }

    /**
     * [1][2][3][...] [null : break]
     *
     * @param sheet      excel sheet. Mast not have null
     * @param startRow   mast not have a negative number
     * @param startColum mast not have a negative number
     * @return int 0 ... Integer.MAX_VALUE
     */
    private int howManyRowInColum(Sheet sheet, int startRow, int startColum) {

        Row row;
        int size = 0;

        while ((row = sheet.getRow(startRow++)) != null && (row.getCell(startColum) != null)) {
            size++;
        }

        return size;
    }

    /**
     * [1]
     * [2]
     * [3]
     * [...]
     * [null : break]
     *
     * @param row        Excel row
     * @param startIndex firs cell
     * @return int 0 ... Integer.MAX_VALUE
     */
    private int howManyCellsInRow(Row row, int startIndex) {

        if (row == null) {
            return 0;
        }

        int cell = 0;
        while (row.getCell(startIndex++) != null) {
            cell++;
        }
        return cell;
    }
}
