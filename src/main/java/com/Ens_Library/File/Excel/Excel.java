package com.Ens_Library.File.Excel;

import com.Ens_Library.File.File_default;
import com.Ens_Library.File.IFileRead;
import com.Ens_Library.File.IFileWrite;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * Class for working with the Excel , writing, reading in a workbook
 */
public class Excel extends File_default implements IFileWrite, IFileRead, IExcel {

    private FileOutputStream fileOutputStream;
    private Workbook workbook;

    private FileInputStream fileInputStream;
    private Sheet selectedSheet;
    private Row selectedRow;
    private Cell selectedCell;


    //------------------------------------------------------------------------------------------------------------------

    /**
     * Directories a file excel if file not exist that create new file
     *
     * @param file
     */
    public Excel(File file) {


        setFile(file);


    }


    //------------------------------------------------------------------------------------------------------------------


    public void fileChange(File newFile) {
        close();
        readAndCloseRead();
        setFile(newFile);

    }

    public void closeAndWrite() {
        writeAndCloseWrite();
        close();
    }

    public void close() {


        if (getWorkbook() != null) {


            try {
                getWorkbook().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            setWorkbook(null);

            setSelectedCell(null);
            setSelectedRow(null);
            setSelectedSheet(null);
        }


    }

    @Override
    public void setFile(File file) {

        super.setFile(file);
    }

    @Override
    public void closeWrite() {

        if (getFileOutputStream() != null) {
            try {
                getFileOutputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            setFileOutputStream(null);
        }

    }


    public void writeAndCloseWrite() {


        buldWriter();

        defaultWoorkBook();

        try {
            getWorkbook().write(getFileOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        closeWrite();
    }


    @Override
    public void buldWriter() {

        if (getFileOutputStream() == null) {

            if (!getFile().exists()) {
                newFile(getFile());
            }


            try {


                setFileOutputStream(new FileOutputStream(getFile()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }


    }

    protected FileOutputStream getFileOutputStream() {
        return fileOutputStream;
    }

    protected void setFileOutputStream(FileOutputStream fileOutputStream) {
        this.fileOutputStream = fileOutputStream;
    }

    protected Workbook getWorkbook() {
        return workbook;
    }

    protected void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }


    @Override
    public void closeRead() {

        if (getFileInputStream() != null) {
            try {
                getFileInputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            setFileInputStream(null);
        }

    }


    public void readAndCloseRead() {

        if (getWorkbook() != null) {
            try {
                getWorkbook().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (getFileInputStream() == null) {
            buildRead();
        }


        if (getFileInputStream() == null) {
            setWorkbook(new XSSFWorkbook());
        } else {
            try {
                setWorkbook(new XSSFWorkbook(getFileInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            closeRead();
        }
    }


    @Override
    public void buildRead() {


        if (getFileInputStream() == null && getFile() != null) {
            try {
                setFileInputStream(new FileInputStream(getFile()));
            } catch (FileNotFoundException e) {

            }
        }


    }


    //------------------------------------------------------------------------------------------------------------------
    protected FileInputStream getFileInputStream() {
        return fileInputStream;
    }

    protected void setFileInputStream(FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }
    //------------------------------------------------------------------------------------------------------------------

    protected void defaultWoorkBook() {
        if (getWorkbook() == null) {
            setWorkbook(new XSSFWorkbook());
        }
    }
    //--------------------------------

    @Override
    public Sheet[] getAllSheet() {
        defaultWoorkBook();

        Sheet[] sheets = new Sheet[(getWorkbook() == null) ? 0 : getWorkbook().getNumberOfSheets()];

        for (int i = 0; i < sheets.length; i++) {
            sheets[i] = getWorkbook().getSheetAt(i);
        }


        return sheets;
    }

    @Override
    public Sheet getSheet(String nameSheet) {
        defaultWoorkBook();

        Sheet sheet;

        sheet = (getWorkbook() == null || nameSheet == null) ? null :
                ((sheet = getWorkbook().getSheet(nameSheet)) == null) ? getWorkbook().createSheet(nameSheet) : sheet;


        setSelectedSheet(sheet);
        setSelectedRow(null);
        setSelectedCell(null);
        return sheet;

    }

    @Override
    public Sheet getSheet() {
        return this.selectedSheet;
    }


    @Override
    public Row getRow(String nameSheet, int indexRow) {
        defaultWoorkBook();

        Sheet sheet;
        Row row;

        row = ((sheet = getSheet(nameSheet)) == null || indexRow < 0) ? null :
                ((row = sheet.getRow(indexRow)) == null) ? sheet.createRow(indexRow) : row;

        setSelectedRow(row);
        setSelectedCell(null);
        return row;
    }

    @Override
    public Row getRow(int indexRow) {

        Row row;

        row = (getSheet() == null || indexRow < 0) ? null :
                ((row = getSheet().getRow(indexRow)) == null) ? getSheet().createRow(indexRow) : row;


        setSelectedRow(row);
        setSelectedCell(null);
        return row;
    }

    @Override
    public Row getRow() {
        return this.selectedRow;
    }

    @Override
    public Cell getCell(String nameSheet, int indexRow, int indexColum) {

        Row row;
        Cell cell;


        cell = ((row = getRow(nameSheet, indexRow)) == null) ? null :
                ((cell = row.getCell(indexColum)) == null) ? row.createCell(indexColum) : cell;


        setSelectedCell(cell);
        return cell;
    }

    @Override
    public Cell getCell(int indexColum) {

        Cell cell;

        cell = (getRow() == null || indexColum < 0) ? null :
                ((cell = getRow().getCell(indexColum)) == null) ? getRow().createCell(indexColum) : cell;

        setSelectedCell(cell);
        return cell;
    }

    @Override
    public Cell getCell() {
        return this.selectedCell;
    }


    protected void setSelectedSheet(Sheet selectedSheet) {
        this.selectedSheet = selectedSheet;
    }


    protected void setSelectedRow(Row selectedRow) {
        this.selectedRow = selectedRow;
    }


    protected void setSelectedCell(Cell selectedCell) {
        this.selectedCell = selectedCell;
    }
    //------------------------------------------------------------------------------------------------------------------
}
