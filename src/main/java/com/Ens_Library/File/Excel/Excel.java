package com.Ens_Library.File.Excel;

import com.Ens_Library.File.File_default;
import com.Ens_Library.File.IFileWrite;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Excel extends File_default implements IFileWrite {

    private FileOutputStream fileOutputStream;
    private Workbook workbook ;

    public Excel(File file) {
        setFile(file);
    }








    public boolean writeAndClose(){
        boolean b = write();

        if (b) {
            close();
        }
        return b;
    }
    public boolean write() {

        if( getFileOutputStream() == null) {
            buldWriter();
        }

        try {
            getWorkbook().write(getFileOutputStream());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    //------------------------------------------------------------------------------------------------------------------

    public void close() {
        closeWrite();

        if (getWorkbook() != null) {
            try {
                getWorkbook().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            setWorkbook(null);
        }
    }

    @Override
    public void setFile(File file) {
        close();

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

    @Override
    public void buldWriter() {

        if (getFileOutputStream() == null) {
            try {
                setFileOutputStream(new FileOutputStream(getFile()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        if (getWorkbook() == null) {
            setWorkbook(new HSSFWorkbook());
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
}
