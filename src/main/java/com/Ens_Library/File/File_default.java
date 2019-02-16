package com.Ens_Library.File;


import java.io.File;
import java.io.IOException;

public class File_default implements IFile {

    private File file;

    @Override
    public File newFile(File path) {

        if (path == null) {
            return path;
        }

        path = notCopuName(path.getAbsolutePath());
        try {
            path.createNewFile();
            setFile(path);
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }

    }

    @Override
    public boolean deleteFile(File f) {

        if (f == null) {
            return true;
        } else {

           setFile((File)null);
           return f.delete();
        }

    }

    @Override
    public File getFile() {
        return this.file;
    }

    @Override
    public void setFile(File file) {
        this.file = file;

    }
}