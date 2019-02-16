package com.Ens_Library.Ens_File;


import java.io.File;
import java.io.IOException;

public interface File_null <T>{


File newFile(String patch, String name) throws IOException;
boolean deleteFile(File f);





}
