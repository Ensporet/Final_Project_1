package com.Lab1.WorkWithFiles.DefaultFile;

import java.io.File;

/**
 * Interface for work to files
 */
public interface IDefaultFile {

    /**
     * Path for program files during runtime
     */
    public final String DIRECTORY_LAB1 = new File("Label").getAbsolutePath() + File.separator,

    /**
     * Path for answers
     */
    FILE_OF_ANSWER = IDefaultFile.DIRECTORY_LAB1 + "ANSWERS.TXT",

    /**
     * Path for questions
     */
    FILE_OF_QUESTIONS = IDefaultFile.DIRECTORY_LAB1 + "QUESTIONS.TXT";


}
