package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Rachel
 * Date: 11/12/13
 * Time: 7:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReadFile {

    public ReadFile(String filePath){
        readInFile(filePath);

    }

    public void readInFile(String filePath){

        BufferedReader reader = null;

        try {
            File file = new File(filePath);
            reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                if(line!=null){
                MoveValidator moveValidator = new MoveValidator(line);

                }
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
